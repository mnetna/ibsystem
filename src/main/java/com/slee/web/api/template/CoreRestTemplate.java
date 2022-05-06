package com.slee.web.api.template;

import com.slee.web.api.model.common.RestTemplateInfo;
import com.slee.web.api.model.core.CoreRequestDto;
import com.slee.web.api.model.core.CoreResponseDto;
import com.slee.web.constant.TokenConstants;
import com.slee.web.handler.JwtTokenHandler;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.config.properties.ApiProperties;
import com.slee.web.util.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoreRestTemplate {
    private final ApiProperties apiProperties;
    private final RestTemplateBuilder restTemplateBuilder;
    private final JwtTokenHandler jwtTokenHandler;

//    public CoreResponseDto post(Object input, SystemInfo sysInfo, RestTemplateInfo restTemplateInfo) throws URISyntaxException {
//        return postForObject(input, sysInfo, restTemplateInfo);
//    }

//    public CoreResponseDto post(Object input, SystemInfo sysInfo, RestTemplateInfo restTemplateInfo) throws URISyntaxException {
//        return postWithBasicAuth(input, sysInfo, restTemplateInfo);
//    }

    public CoreResponseDto post(Object input, SystemInfo sysInfo, RestTemplateInfo restTemplateInfo) throws URISyntaxException {
        try {
            return postWithJwtToken(input, sysInfo, restTemplateInfo);
        } catch (HttpClientErrorException.Unauthorized e1) {
            log.error("There was an HttpClientErrorException =>", e1);
            jwtTokenHandler.refreshAccessToken(TokenConstants.APIGATEWAY_USERID);
            return postWithJwtToken(input, sysInfo, restTemplateInfo);
        }
    }

    public CoreResponseDto postForObject(Object input, SystemInfo sysInfo, RestTemplateInfo restTemplateInfo) throws URISyntaxException {
        return restTemplateBuilder.build().postForObject(
                new URI(restTemplateInfo.makeURL(apiProperties)),
                new HttpEntity<>(CoreRequestDto.builder().input(input).sysInfo(sysInfo).build(), RestTemplateUtil.makeHeader()),
                CoreResponseDto.class);
    }

    public CoreResponseDto postWithBasicAuth(Object input, SystemInfo sysInfo, RestTemplateInfo restTemplateInfo) throws URISyntaxException {
        return restTemplateBuilder.build().postForObject(
                new URI(restTemplateInfo.makeURL(apiProperties)),
                new HttpEntity<>(CoreRequestDto.builder().input(input).sysInfo(sysInfo).build(), RestTemplateUtil.makeHeaderWithBasicAuth()),
                CoreResponseDto.class);
    }

    public CoreResponseDto postWithJwtToken(Object input, SystemInfo sysInfo, RestTemplateInfo restTemplateInfo) throws URISyntaxException {
        return restTemplateBuilder.build().postForObject(
                new URI(restTemplateInfo.makeURL(apiProperties)),
                new HttpEntity<>(CoreRequestDto.builder().input(input).sysInfo(sysInfo).build(), RestTemplateUtil.makeHeaderWithJwtToken(TokenConstants.APIGATEWAY_USERID)),
                CoreResponseDto.class);
    }
}
