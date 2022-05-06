package com.slee.web.common.model.auth;

import com.slee.web.constant.ApiDefine;
import com.slee.web.jpa.entity.auth.MemberDetail;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDetailRequestDto {
    /** 은행 ID **/
    @NotNull
    private String iBankId;
    /** 은행 코드 **/
    @NotNull
    private String bankCd;
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
    /** 주소타입 **/
    private String adrTypCd;
    /** 주소 **/
    private String baseAdr;
    /** 우편번호 **/
    private String zipNo;

    public MemberDetail toMemberDetail(boolean isRegist) {
        return MemberDetail.builder()
                .iBankId(iBankId)
                .bankCd(ApiDefine.Bank.valueOf(bankCd))
                .custNo(custNo)
                .custDvCd(custDvCd)
                .custInfoKindCd(custInfoKindCd)
                .mngBrNo(mngBrNo)
                .custNm(custNm)
                .custEngNm(custEngNm)
                .cntyCd(cntyCd)
                .gndrDvCd(gndrDvCd)
                .btdy(btdy)
                .emalAdr(emalAdr)
                .adrTypCd(adrTypCd)
                .baseAdr(baseAdr)
                .zipNo(zipNo)
                .registYn(isRegist?"Y":"N")
                .build();
    }
}
