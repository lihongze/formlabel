package com.github.formlabel.dao;

import com.github.formlabel.annotation.ConfigFormField;
import com.github.formlabel.annotation.ConfigGroup;
import com.github.formlabel.annotation.Rule;
import com.github.formlabel.annotation.constant.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongze
 * @date 2021-07-29 16:04:32
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsideConfig {
    @ConfigFormField(type = TypeEnum.RADIO, label = "是否开启", options = {"否", "是"})
    @Rule(rules = {"{ \"required\": true, \"type\":\"number\", \"message\": \"是否打开当前配置项\", \"trigger\": \"change\" }"})
    private Integer needMatch;

    @ConfigGroup(label = "内层配置项")
    private LoopConfig loopConfig;
}
