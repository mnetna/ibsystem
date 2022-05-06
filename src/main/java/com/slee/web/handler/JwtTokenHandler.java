package com.slee.web.handler;

import com.slee.web.common.model.jwt.JwtTokenRequestDto;
import com.slee.web.common.model.jwt.JwtTokenResponseDto;
import com.slee.web.constant.TokenConstants;
import com.slee.web.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class JwtTokenHandler {

     public String getAccessToken(String key) {
        log.info("Token 재발급 EXECUTE!!!");
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpEntity<JwtTokenResponseDto> response = restTemplate.exchange(
                    new URI("http://localhost:8091/auth/login")
                    , HttpMethod.POST
                    , new HttpEntity<>(JwtTokenRequestDto.builder().userId(TokenConstants.APIGATEWAY_USERID).password(TokenConstants.APIGATEWAY_PASSWORD).build(), RestTemplateUtil.makeHeader())
                    , JwtTokenResponseDto.class);

            String accessToken = response.getBody().getAccessToken();
            String refreshToken = response.getBody().getRefreshToken();
            JwtTokenInfo jwtTokenInfo = JwtTokenInfo.builder().accessToken(accessToken).refreshToken(refreshToken).build();
            JwtTokenManager.setToken(key, jwtTokenInfo);
            log.info("Token Issue Success!!");
            return accessToken;
        } catch (RestClientException | URISyntaxException exception) {
            log.error("Token 발급 요청 중 에러 발생!!", exception);
            throw new RuntimeException(exception.getMessage());
        }
    }

    public String refreshAccessToken(String key) {
        log.info("Token 갱신 EXECUTE!!!");
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpEntity<JwtTokenResponseDto> response = restTemplate.exchange(
                    new URI("http://localhost:8091/auth/reset")
                    , HttpMethod.GET
                    , new HttpEntity<>(RestTemplateUtil.makeHeaderWithJwtToken(TokenConstants.APIGATEWAY_USERID))
                    , JwtTokenResponseDto.class);

            String accessToken = response.getBody().getAccessToken();
            String refreshToken = response.getBody().getRefreshToken();
            JwtTokenInfo jwtTokenInfo = JwtTokenInfo.builder().accessToken(accessToken).refreshToken(refreshToken).build();
            JwtTokenManager.setToken(key, jwtTokenInfo);
            log.info("Token Refresh Success!!");
            return accessToken;
        } catch (RestClientException | URISyntaxException exception) {
            log.error("Token 갱신 요청 중 에러 발생!!", exception);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
