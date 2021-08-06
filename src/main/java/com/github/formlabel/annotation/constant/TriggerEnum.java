package com.github.formlabel.annotation.constant;

import lombok.Getter;

/**
 * @author hongze
 * @date 2021-07-29 14:10:32
 * @apiNote
 */
public enum TriggerEnum {
    CHANGE("change"),
    BLUR("blur");

    @Getter
    private final String value;

    TriggerEnum(String str) {
        this.value = str;
    }
}
