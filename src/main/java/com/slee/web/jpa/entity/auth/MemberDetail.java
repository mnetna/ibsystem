package com.slee.web.jpa.entity.auth;

import com.slee.web.constant.ApiDefine;
import lombok.*;

import javax.persistence.*;

@IdClass(MemberDetailPk.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member_detail")
@Entity
public class MemberDetail {

	/** 은행 ID **/
    @Id
    @Column(nullable = false)
    private String iBankId;

    /** 은행 코드 **/
    @Id
    @Enumerated(EnumType.STRING)
    private ApiDefine.Bank bankCd;

    /** 고객번호 **/
    @Column
    private String custNo;

    /** 고객구분코드 **/
    @Column
    private String custDvCd;

    /** 고객정보종류코드 */
    @Column
    private String custInfoKindCd;

    /** 관리점번호 */
    @Column
    private String mngBrNo;

    /** 고객명 */
    @Column
    private String custNm;

    /** 고객영문명 */
    @Column
    private String custEngNm;

    /** 국가코드 */
    @Column
    private String cntyCd;

    /** 성별구분코드 **/
    @Column
    private String gndrDvCd;

    /** 생년월일 **/
    @Column
    private String btdy;

    /** 이메일 **/
    @Column
    private String emalAdr;

    /** 주소타입 **/
    @Column
    private String adrTypCd;

    /** 주소 **/
    @Column
    private String baseAdr;

    /** 우편번호 **/
    @Column
    private String zipNo;

    /** 회원가입 완료여부 **/
    @Column
    private String registYn;

}
