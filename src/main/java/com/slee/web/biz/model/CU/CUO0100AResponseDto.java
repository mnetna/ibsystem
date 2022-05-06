package com.slee.web.biz.model.CU;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CUO0100AResponseDto {

    // Base
    /** Transaction Type */
    private String procTyp;
    /** Customer Type */
    private String custTyp;
    /** 고객번호 */
    private String custNo;
    /** 고객구분코드 */
    private String custDvCd;
    /** 고객정보종류코드 */
    private String custInfoKindCd;
    /** 고객상태코드 */
    private String custStCd;
    /** 신분증종류코드 */
    private String idcdKindCd;
    /** 실명번호 */
    private String acnmNo;
    /** 관리점번호 */
    private String mngBrNo;
    /** Expire date of ID */
    private String certsEndDt;
    /** 고객명 */
    private String custNm;
    /** 고객약어명 */
    private String custAbbrNm;
    /** 고객영문명 */
    private String custEngNm;
    /** 고객영문약어명 */
    private String custEngAbbrNm;
    /** 국가코드 */
    private String cntyCd;
    /** 거주국가코드 */
    private String domclCntyCd;
    /** 모회사국가코드 */
    private String momCoCntyCd;
    /** 모은행 BIC 코드 */
    private String prtsBnkBicCd;
    /** 납세자번호 */
    private String npwp;
    /** Business Type Large class code */
    private String tpbzClasCdLclas;
    /** Business Type code */
    private String tpbzClasCd;
    /** Risk Based Approach */
    private String rskDvCd;
    /** remarks 1 */
    private String rmk1;
    /** remarks 2 */
    private String rmk2;

    // Individual
    /** 성별구분코드 **/
    private String gndrDvCd;
    /** 생년월일 **/
    private String btdy;

    // Address and Tell Info (List)
    private ArrayList<CugMgntCustCtfcIOGrid01> grid01 = new ArrayList<>();
}
