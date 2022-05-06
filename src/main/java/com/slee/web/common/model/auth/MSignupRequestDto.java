package com.slee.web.common.model.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MSignupRequestDto {
    private String userid;
    private String bankCd;
    private String pin;
    private String deviceUuid;

    public String toEncryptPin(PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(pin);
    }
}
