package com.github.formlabel.annotation;

import com.github.formlabel.annotation.constant.TypeEnum;

import java.lang.annotation.*;

/**
 * @author hongze
 * @date 2021-7-28 14:23:05
 * @apiNote
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormField {
    TypeEnum type() default TypeEnum.TEXT;

    String label();

    String[] options() default {};
}
