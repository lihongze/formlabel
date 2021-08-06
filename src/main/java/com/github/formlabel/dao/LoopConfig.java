package com.github.formlabel.dao;

import com.github.formlabel.annotation.ConfigFormField;
import com.github.formlabel.annotation.Rule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoopConfig {
    @ConfigFormField(label = "配置项")
    @Rule(rules = {"{ \"required\": true, \"message\": \"请填写该项配置\", \"trigger\": \"change\" }",
            "{ \"min\": 1, \"max\": 20, \"type\":\"number\", \"message\": \"配置大小在1-20之间\", \"trigger\": \"change\" }"})
    private Integer configName;

}
