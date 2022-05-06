package com.slee.web.common.model.auth;

import com.slee.web.jpa.entity.auth.Member;
import com.slee.web.jpa.entity.auth.MemberDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    /** 유저 ID **/
    private String userid;
    /** 언어구분코드 **/
    private String lnggDvCd;
    /** 은행 ID **/
    private String bankId;
    /** 고객번호 **/
    private String custNo;
    /** 고객구분코드 **/
    private String custDvCd;
    /** 고객정보종류코드 */
    private String custInfoKindCd;
    /** 관리점번호 */
    private String mngBrNo;
    /** 고객명 */
    private String custNm;
    /** 고객영문명 */
    private String custEngNm;
    /** 국가코드 */
    private String cntyCd;
    /** 성별구분코드 **/
    private String gndrDvCd;
    /** 생년월일 **/
    private String btdy;
    /** 이메일 **/
    private String emalAdr;
    /** 주소코드 **/
    private String adrStCd;
    /** 주소 **/
    private String baseAdr;
    /** 우편번호 **/
    private String zipNo;

    public static MemberResponseDto of(Member member, MemberDetail memberDetail) {
        return new MemberResponseDto(
                member.getUserid(),
                member.getLnggDvCd(),
                memberDetail.getIBankId(),
                memberDetail.getCustNo(),
                memberDetail.getCustDvCd(),
                memberDetail.getCustInfoKindCd(),
                memberDetail.getMngBrNo(),
                memberDetail.getCustNm(),
                memberDetail.getCustEngNm(),
                memberDetail.getCntyCd(),
                memberDetail.getGndrDvCd(),
                memberDetail.getBtdy(),
                memberDetail.getEmalAdr(),
                memberDetail.getAdrTypCd(),
                memberDetail.getBaseAdr(),
                memberDetail.getZipNo()
        );
    }
}
