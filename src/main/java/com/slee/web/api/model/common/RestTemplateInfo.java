package com.slee.web.api.model.common;

import com.slee.web.constant.ApiDefine;
import com.slee.web.util.SecurityUtil;
import com.slee.web.config.properties.ApiProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestTemplateInfo {
    ApiDefine.Target target;
    ApiDefine.Bank bank;
    ApiDefine.ServiceCode serviceCode;

    @Builder
    public RestTemplateInfo(ApiDefine.Target target, ApiDefine.Bank bank, ApiDefine.ServiceCode serviceCode) {
        this.target = target;
        this.bank = bank != null? bank : ApiDefine.Bank.valueOf(SecurityUtil.getBankCdByContext());
        this.serviceCode = serviceCode;
    }

    public String makeURL(ApiProperties apiProperties) {
        return apiProperties.getUrlByCode(target)
                .concat(apiProperties.getBankByCode(bank)
                .concat(apiProperties.getApiByCode(serviceCode)));
    }
}
