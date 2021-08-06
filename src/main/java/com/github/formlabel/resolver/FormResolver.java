package com.github.formlabel.resolver;


import com.github.formlabel.annotation.FormField;
import com.github.formlabel.annotation.FormGroup;
import com.github.formlabel.annotation.Rule;
import com.github.formlabel.dao.BaseConfig;
import com.github.formlabel.data.FormFieldInfo;
import com.github.formlabel.data.FormData;
import com.github.formlabel.data.Option;
import com.github.formlabel.utils.JsonUtils;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author hongze
 * @date 2021-07-28 14:26:23
 * @apiNote
 */
@Slf4j
public class FormResolver {
    @SneakyThrows
    // 讲对象转为配置表单
    public static FormData resolve(BaseConfig ob) {
        FormData formData = new FormData();
        Map<String,Object> data = new LinkedHashMap<>();
        dealFields(data,ob);
        formData.setKey(ob.getKey());
        formData.setList((List<Map<String, Object>>) data.getOrDefault("list",Lists.newLinkedList()));
        return formData;
    }

    // 参数解析
    @SneakyThrows
    private static void dealFields(Map<String,Object> data,Object ob) {
        Field[] fields = ob.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            FormGroup formGroup = field.getAnnotation(FormGroup.class);
            FormField formField = field.getAnnotation(FormField.class);
            // configGroup不为空 说明该属性是复杂对象 递归解析
            if (formGroup != null) {
                Map<String,Object> inside = new LinkedHashMap<>();
                inside.put(FormFieldInfo.LABEL, formGroup.label());
                List<Map<String,Object>> list = (List<Map<String, Object>>) data.getOrDefault("list",new LinkedList<>());
                list.add(inside);
                data.put("list",list);
                if (field.get(ob) == null) {
                    continue;
                }
                // 递归
                dealFields(inside,field.get(ob));
            } else if (formField != null) {
                resolveField(data,field, formField,ob);
            }
        }
    }

    // 解析包装基本类型属性
    private static void resolveField(Map<String,Object> data, Field field, FormField formField, Object ob) throws IllegalAccessException {
        // 获取字段分组
        data.put("groupName",ob.getClass().getSimpleName());
        List<Map<String,Object>> list = (List<Map<String, Object>>) data.getOrDefault("list",new LinkedList<>());
        // 根据注解构造 FieldInfo 对象
        Map<String,Object> fieldInfo = new LinkedHashMap<>();
        // 填充属性值
        fieldInfo.put(FormFieldInfo.LABEL, formField.label());
        fieldInfo.put(FormFieldInfo.TYPE, formField.type().getValue());
        fieldInfo.put(FormFieldInfo.VALUE,field.get(ob));
        fieldInfo.put(FormFieldInfo.NAME,field.getName());
        // 填充 options
        String[] options = formField.options();
        List<Option> optionList = new ArrayList<>(options.length);
        int value  = 0;
        for (String optionValue : options) {
            Option option = new Option();
            option.setValue(value++);
            option.setLabel(optionValue);
            optionList.add(option);
        }
        fieldInfo.put("options", optionList);
        // 填充rule规则
        Rule rule = field.getAnnotation(Rule.class);
        if (rule != null) {
            List<Object> rules = Lists.newLinkedList();
            for(String s : rule.rules()) {
                rules.add(JsonUtils.json2Object(s,Map.class));
            }
            fieldInfo.put(FormFieldInfo.RULE,rules);
        }
        list.add(fieldInfo);
        data.put("list",list);
    }

    // 将配置表单转为指定对象
    public static <T extends BaseConfig> T parse(FormData formData, Class<T> tClass) {
        T t ;
        try {
            List<Map<String,Object>> data = formData.getList();
            t = tClass.getDeclaredConstructor().newInstance();
            parseFields(data,t);
            if (Strings.isBlank(formData.getKey())) {
                throw new RuntimeException("更新key为空！");
            }
            t.setKey(formData.getKey());
            return t;
        } catch (Exception e) {
            log.error("formData parse to {} fail!",tClass.getName(),e);
        }
        return null;
    }

    // 递归填充属性值
    private static void parseFields(List<Map<String,Object>> list,Object ob) {
        Field[] fields = ob.getClass().getDeclaredFields();
        try {
            for(Field field:fields) {
                field.setAccessible(true);
                FormGroup formGroup = field.getAnnotation(FormGroup.class);
                FormField formField = field.getAnnotation(FormField.class);
                if (formGroup != null) {
                    field.set(ob,field.getType().getConstructor().newInstance());
                    for(Map<String,Object> map : list) {
                        if (field.getType().getSimpleName().equals(map.get("groupName"))) {
                            parseFields((List<Map<String, Object>>) map.get("list"),field.get(ob));
                        }
                    }
                } else if (formField != null) {
                    // 根据注解构造 FormFieldInfo 对象
                    for(Map<String,Object> map : list) {
                        if (field.getName().equals(map.get(FormFieldInfo.NAME))) {
                            setField(field,ob,map.get(FormFieldInfo.VALUE));
                        }
                    }
                }
            }
        }catch (Exception e) {
            log.error("formData set field value fail!",e);
        }
    }

    public static void setField(Field field, Object o, Object data) throws IllegalAccessException {
        if (data == null){
            return;
        }
        Class<?> type = field.getType();
        if (type == Integer.class){
            field.set(o, Integer.valueOf(String.valueOf(data)));
        } else if (type == Long.class){
            field.set(o, Long.valueOf(String.valueOf(data)));
        } else if (type == Byte.class){
            field.set(o, Byte.valueOf(String.valueOf(data)));
        } else if (type == Short.class){
            field.set(o, Short.valueOf(String.valueOf(data)));
        } else if (type == Character.class){
            field.set(o, data);
        } else if (type == Boolean.class){
            field.set(o, Boolean.valueOf(String.valueOf(data)));
        } else if (type == Float.class){
            field.set(o, Float.valueOf(String.valueOf(data)));
        } else if (type == Double.class){
            field.set(o, Double.valueOf(String.valueOf(data)));
        } else if (type == String.class){
            field.set(o, String.valueOf(data));
        } else {
            field.set(o, data);
        }
    }
}
