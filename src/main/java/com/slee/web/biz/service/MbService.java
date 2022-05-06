package com.slee.web.biz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slee.web.biz.model.MB.MBO0100AResponseDto;
import com.slee.web.constant.ApiDefine;
import com.slee.web.api.model.common.RestTemplateInfo;
import com.slee.web.api.template.CoreRestTemplate;
import com.slee.web.biz.model.MB.MBO0100ARequestDto;
import com.slee.web.biz.model.MB.MBO0200ARequestDto;
import com.slee.web.biz.model.MB.MBO0200AResponseDto;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.api.model.core.CoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MbService {
    private final CoreRestTemplate coreRestTemplate;
    private final ObjectMapper objectMapper;

    public List<MBO0100AResponseDto> executeMBO0100A(MBO0100ARequestDto param, SystemInfo systemInfo, String target) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(param, systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(target))
                        .serviceCode(ApiDefine.ServiceCode.MBO0100A)
                        .build());
        return ((List<Object>) coreResponseDto.getOutput().get("accounts"))
                .stream()
                .map(x -> objectMapper.convertValue(x, MBO0100AResponseDto.class))
                .collect(Collectors.toList());
    }

    public MBO0200AResponseDto executeMBO0200A(MBO0200ARequestDto param, SystemInfo systemInfo, String target) throws URISyntaxException {
        CoreResponseDto coreResponseDto = coreRestTemplate.post(param, systemInfo,
                RestTemplateInfo.builder()
                        .target(ApiDefine.Target.valueOf(target))
                        .serviceCode(ApiDefine.ServiceCode.MBO0200A)
                        .build());
        return objectMapper.convertValue(coreResponseDto.getOutput(), MBO0200AResponseDto.class);
    }
}
