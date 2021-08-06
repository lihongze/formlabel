package com.github.formlabel.dao;

import com.github.formlabel.annotation.ConfigGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongze
 * @date 2021-07-28
 * @apiNote 匹配配置类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigForm implements BaseConfig {

    private String key;

    @ConfigGroup(label = "配置")
    private InsideConfig insideConfig;


}
