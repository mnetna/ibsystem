package com.slee.web.common.model.jwt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
