package com.github.formlabel.annotation.constant;

import lombok.Getter;

/**
 * @author hongze
 * @date 2021-07-29 14:10:32
 * @apiNote
 */
public enum TypeEnum {
    TEXT("text"),
    RADIO("radio"),
    CHECKBOX("checkbox");

    @Getter
    private String value;

    TypeEnum(String str) {
        this.value = str;
    }


}
