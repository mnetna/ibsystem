package com.slee.web.biz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slee.web.api.model.common.RestTemplateInfo;
import com.slee.web.api.model.core.CoreResponseDto;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.api.template.CoreRestTemplate;
import com.slee.web.biz.model.DP.DPO0100A06ResponseDto;
import com.slee.web.biz.model.DP.DPO0100ARequestDto;
import com.slee.web.biz.model.DP.DPO0100AResponseDto;
import com.slee.web.constant.ApiDefine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DpService {
    private final CoreRestTemplate coreRestTemplate;
    private final ObjectMapper objectMapper;

    public DPO0100AResponseDto excuteDPO0100A(DPO0100ARequestDto param, SystemInfo systemInfo, String target) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(param, systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(target))
                        .serviceCode(ApiDefine.ServiceCode.DPO0100A)
                        .build());
        return objectMapper.convertValue(coreResponseDto.getOutput().get("baseInfo"), DPO0100AResponseDto.class);
    }

    public List<DPO0100A06ResponseDto> excuteDPO0100A06(DPO0100ARequestDto param, SystemInfo systemInfo, String target) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(param, systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(target))
                        .serviceCode(ApiDefine.ServiceCode.DPO0100A)
                        .build());
        return ((List<Object>) coreResponseDto.getOutput().get("grid02"))
                .stream()
                .map(x -> objectMapper.convertValue(x, DPO0100A06ResponseDto.class))
                .collect(Collectors.toList());
    }
}
