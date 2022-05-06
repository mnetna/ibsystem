package com.slee.web.common.service;

import com.slee.web.config.auth.model.TokenDto;
import com.slee.web.config.auth.jwt.JwtTokenProvider;
import com.slee.web.jpa.entity.auth.RefreshToken;
import com.slee.web.jpa.repository.auth.RefreshTokenRepository;
import com.slee.web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto login() {
        return generateToken(SecurityUtil.getAuthentication());
    }

    private TokenDto generateToken(Authentication authentication) {
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }
}
