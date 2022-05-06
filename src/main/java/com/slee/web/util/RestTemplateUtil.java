package com.slee.web.util;

import com.slee.web.config.ApplicationContextProvider;
import com.slee.web.handler.JwtTokenHandler;
import com.slee.web.handler.JwtTokenInfo;
import com.slee.web.handler.JwtTokenManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestTemplateUtil {

    public static HttpHeaders makeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static HttpHeaders makeHeaderWithJwtToken(String key) {
        JwtTokenHandler jwtTokenHandler = (JwtTokenHandler) ApplicationContextProvider.getApplicationContext().getBean("jwtTokenHandler");

        if (!JwtTokenManager.hasToken(key)) {
            jwtTokenHandler.getAccessToken(key);
        }

        JwtTokenInfo jwtTokenInfo = JwtTokenManager.getToken(key);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", jwtTokenInfo.getAccessToken());
        headers.set("X-Refresh-Token", jwtTokenInfo.getRefreshToken());
        return headers;
    }

    public static HttpHeaders makeHeaderWithBasicAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("");
        return headers;
    }
}
