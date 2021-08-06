package com.github.formlabel.annotation;


import java.lang.annotation.*;

/**
 * @author hongze
 * @date 2021-07-29 14:08:22
 * @apiNote
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Rule {
    String[] rules() default {};
}
