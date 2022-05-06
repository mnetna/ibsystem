package com.slee.web.biz.model.CU;


import lombok.Getter;

@Getter
public class CugMgntCustCtfcIOGrid01 {
    /** Operation Type */
    private String opKind;
    /** Address Type **/
    private String adrTypCd;
    /** Sequence */
    private String seqNo;
    /** Status code */
    private String sts;
    /** Address Status code */
    private String adrStCd;
    /** Main Address Flag */
    private String mainYn;
    /** Wrong Address Flag */
    private String maigRecvPossYn;
    /** Address Country */
    private String adrCntyCd;
    /** Address States */
    private String adrSttCd;
    /** POST NO */
    private String zipNo;
    /** Address (eng) */
    private String baseAdr;
    /** Address (eng) */
    private String exAdr;
    /** Address (LOCAL) */
    private String localAdr1;
    /** Address (LOCAL) */
    private String localAdr2;
    /** Mail Receive Falg */
    private String maigRecvYn;
    /** Tel Country No */
    private String telCntyNo;
    /** Telephone no */
    private String telNo;
    /** Fax Country No */
    private String faxCntyNo;
    /** FAX number */
    private String faxNo;
    /** Mobile Country No */
    private String mbleMbphCntyNo;
    /** Mobile number */
    private String mbleMbphNo;
    /** SMS Receive Flag */
    private String smsRecvYn;
    /** Additional Mobile Country No */
    private String mbleMbphNoCtry2;
    /** Additional Mobile number */
    private String mbleMbphNo2;
    /** Additional SMS Receive Flag */
    private String smsRecvYn2;
    /** E-Mail Addr */
    private String emalAdr;
    /** E-Mail Receive Flag */
    private String emalRecvYn;
    /** Neigbourhoud area */
    private String rt;
    /** Complex site area */
    private String rw;
    /** Vilage type */
    private String vlgNm;
    /** Town type */
    private String townNm;
    /** City name */
    private String cityNm;
    /** Usage location code */
    private String datiIi;
    /** Province code */
    private String prvncNm;
    /** Family's name */
    private String famNm;
    /** Family's relationship name */
    private String famRelNm;

}
