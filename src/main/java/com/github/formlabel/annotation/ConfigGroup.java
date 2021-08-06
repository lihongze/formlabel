package com.github.formlabel.annotation;

import java.lang.annotation.*;

/**
 * @author hongze
 * @date 2021-07-28 12:04:00
 * @apiNote
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigGroup {
    String label();
}
