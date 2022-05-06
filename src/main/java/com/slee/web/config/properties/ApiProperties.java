package com.slee.web.config.properties;

import com.slee.web.constant.ApiDefine;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@RefreshScope
@Component
@Getter @Setter
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private String server;
    private Map<String, String> eai;
    private Map<String, String> gateway;
    private Map<String, String> bank;
    private Map<String, String> service;

    public String getBankByCode(ApiDefine.Bank bank) {
        return this.bank.get(bank.name());
    }

    public String getApiByCode(ApiDefine.ServiceCode apiCode) {
        return this.service.get(apiCode.name());
    }

    public String getUrlByCode(ApiDefine.Target target) {
        switch (this.server) {
            case "eai" :
                return getEaiByCode(target);
            case "gateway" :
                return getGatewayByCode(target);
            default:
                return null;
        }
    }

    private String getEaiByCode(ApiDefine.Target target) {
        return eai.get(target.name());
    }

    private String getGatewayByCode(ApiDefine.Target target) {
        return gateway.get(target.name());
    }

}
