package com.slee.web.biz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slee.web.constant.ApiDefine;
import com.slee.web.api.model.common.RestTemplateInfo;
import com.slee.web.api.template.CoreRestTemplate;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.api.model.core.CoreResponseDto;
import com.slee.web.biz.model.LN.LNO0100ARequestDto;
import com.slee.web.biz.model.LN.LNO0100AResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LnService {
    private final CoreRestTemplate coreRestTemplate;
    private final ObjectMapper objectMapper;

	public LNO0100AResponseDto excuteLNO0100A(LNO0100ARequestDto param, SystemInfo systemInfo, String target) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(param, systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(target))
                        .serviceCode(ApiDefine.ServiceCode.LNO0100A)
                        .build());
        return objectMapper.convertValue(coreResponseDto.getOutput().get("loanBascInfo"), LNO0100AResponseDto.class);
    }

    //임시
    public List<Object> excuteLNO0100AT3(LNO0100ARequestDto param, SystemInfo systemInfo, String target) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(param, systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(target))
                        .serviceCode(ApiDefine.ServiceCode.LNO0100A)
                        .build());
        return (List<Object>) coreResponseDto.getOutput().get("gridT3");
    }
}
