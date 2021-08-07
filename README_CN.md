FormLabel
=========
实现了一组自定义注解，可以将后端 pojo 类转换为前端表单配置数据格式 
包含了几个自定义的核心标签 **@FormField、
@FormGroup、@Rule**,以及一个**FormResolver** 类做标签解析和包装，可以实现将业务模型类转化为前端可识别的表单数据结构，适用于管理后台复杂配置表单的转化场景

### @FormField (用于标识单个配置项)
 * type : 表单类型 （text、radio、checkbox）
 * label : 描述
 * options : 配置项

### @FormGroup (用于标识内嵌配置组)
 * label : 描述

### @Rule (用于标识配置项校验规则)
 * required : 是否必填
 * type : 字段类型（etc:number）
 * message : 提示信息
 * trigger : 触发器（change、blur）

**注：**
  rule标签属性暂未定义实体类规范化，属性可随前端需求灵活变动

  
