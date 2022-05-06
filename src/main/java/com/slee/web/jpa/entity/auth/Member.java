package com.slee.web.jpa.entity.auth;

import com.slee.web.constant.ApiDefine;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
@Entity
public class Member {

    /** 은행 별 사용자 ID **/
    @Id
    @Column(nullable = false)
    private String userid;

    /** 사용자 ID **/
    @Column
    private String orgUserid;

    /** 언어구분코드 **/
    @Column
    private String lnggDvCd;

    /** 은행 ID **/
    @Column
    private String iBankId;

    /** 은행 코드 **/
    @Enumerated(EnumType.STRING)
    private ApiDefine.Bank bankCd;

    /** 권한 **/
    @Enumerated(EnumType.STRING)
    private Role role;

    /** 패스워드 **/
    @Column
    private String password;

    /** PIN 인증 코드 **/
    @Column
    private String pin;
}
