package com.slee.web.biz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slee.web.constant.ApiDefine;
import com.slee.web.api.model.common.RestTemplateInfo;
import com.slee.web.api.model.core.CoreResponseDto;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.api.template.CoreRestTemplate;
import com.slee.web.biz.model.CU.CUO0100ARequestDto;
import com.slee.web.biz.model.CU.CUO0100AResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class CuService {
    private final CoreRestTemplate coreRestTemplate;
    private final ObjectMapper objectMapper;

    public CUO0100AResponseDto getCUO0100A(CUO0100ARequestDto param, SystemInfo systemInfo, String apiTarget, String bankTarget) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(
                param,
                systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(apiTarget))
                        .bank(ApiDefine.Bank.valueOf(bankTarget))
                        .serviceCode(ApiDefine.ServiceCode.CUO0100A)
                        .build());
        return objectMapper.convertValue(coreResponseDto.getOutput(), CUO0100AResponseDto.class);
    }
}
