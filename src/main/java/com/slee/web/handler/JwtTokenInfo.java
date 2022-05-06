package com.slee.web.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtTokenInfo {
    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtTokenInfo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
