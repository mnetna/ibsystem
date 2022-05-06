package com.slee.web.common.model.auth;

import com.slee.web.constant.ApiDefine;
import com.slee.web.jpa.entity.auth.Role;
import com.slee.web.jpa.entity.auth.Member;
import com.slee.web.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    // TODO 임시 값
    private String srchCustNo;

    private String userid;
    private String intrBankId;
    private String bankCd;
    private String password;
    private int acctNo;
    private int dbtCardNo;
    private String dbtCardPw;
    private int nik;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userid(SecurityUtil.makeUserid(userid, bankCd))
                .orgUserid(userid)
                .password(passwordEncoder!=null?passwordEncoder.encode(password):null)
                .iBankId(intrBankId)
                .bankCd(ApiDefine.Bank.valueOf(bankCd))
                .lnggDvCd("ko")
                .role(Role.ROLE_USER)
                .build();
    }
}
