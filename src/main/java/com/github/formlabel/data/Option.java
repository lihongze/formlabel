package com.github.formlabel.data;

import lombok.Data;

/**
 * @author hongze
 * @date 2021-07-28 14:43:25
 * @apiNote
 */
@Data
public class Option {

    /**
     * 键
     */
    private Integer value;

    /**
     * 描述信息
     */
    private String label;
}
