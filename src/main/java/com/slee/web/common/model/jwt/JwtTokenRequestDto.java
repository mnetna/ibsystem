package com.slee.web.common.model.jwt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenRequestDto {
    private String userId;
    private String password;
}
