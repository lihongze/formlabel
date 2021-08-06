package com.github.formlabel;

import com.github.formlabel.dao.ConfigForm;
import com.github.formlabel.dao.InsideConfig;
import com.github.formlabel.dao.LoopConfig;
import com.github.formlabel.data.FormData;
import com.github.formlabel.resolver.FormResolver;
import com.github.formlabel.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class FormlabelApplicationTests {

    private String TEST_FORM_DATA = "{\"list\":[{\"label\":\"配置\",\"groupName\":\"InsideConfig\"," +
            "\"list\":[{\"label\":\"是否开启\",\"type\":\"radio\",\"value\":1,\"name\":\"needMatch\"," +
            "\"options\":[{\"value\":0,\"label\":\"否\"},{\"value\":1,\"label\":\"是\"}],\"rule\":" +
            "[{\"required\":true,\"type\":\"number\",\"message\":\"是否打开当前配置项\",\"trigger\":" +
            "\"change\"}]},{\"label\":\"内层配置项\",\"groupName\":\"LoopConfig\",\"list\":[{\"label\"" +
            ":\"配置项\",\"type\":\"text\",\"value\":5,\"name\":\"configName\",\"options\":[],\"rule\":" +
            "[{\"required\":true,\"message\":\"请填写该项配置\",\"trigger\":\"change\"},{\"min\":1,\"max\"" +
            ":20,\"type\":\"number\",\"message\":\"配置大小在1-20之间\",\"trigger\":\"change\"}]}]}]}]," +
            "\"key\":\"updateKey\"}";

    @Test
    void resolveTest() {
        ConfigForm configForm = ConfigForm.builder()
                .key("updateKey")
                .insideConfig(InsideConfig.builder()
                        .needMatch(1)
                        .loopConfig(LoopConfig.builder()
                                .configName(5)
                                .build())
                        .build())
                .build();
        FormData formData = FormResolver.resolve(configForm);
        log.info(JsonUtils.object2Json(formData));
    }

    @Test
    void parseTest() {
        FormData formData = JsonUtils.json2Object(TEST_FORM_DATA,FormData.class);
        ConfigForm configForm = FormResolver.parse(formData,ConfigForm.class);
        log.info(JsonUtils.object2Json(configForm));
    }
}
