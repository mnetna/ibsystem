package com.slee.web.common.model.auth;

import com.slee.web.util.SecurityUtil;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
    private String userid;
    private String bankCd;
    private String password;
    private String deviceUuid;
    private String pin;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(SecurityUtil.makeUserid(userid, bankCd), password);
    }

    public UsernamePasswordAuthenticationToken toPinAuthentication() {
        return new UsernamePasswordAuthenticationToken(SecurityUtil.makeUserid(userid, bankCd), pin);
    }
}
