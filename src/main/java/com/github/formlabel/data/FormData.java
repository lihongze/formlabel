package com.github.formlabel.data;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author hongze
 * @date 2021-07-28 14:32:11
 * @apiNote
 */
@Data
public class FormData {
    public FormData() {
        this.list = new LinkedList<>();
    }

    private List<Map<String, Object>> list;

    private String key;
}
