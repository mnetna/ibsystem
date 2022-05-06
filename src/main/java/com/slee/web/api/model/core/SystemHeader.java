package com.slee.web.api.model.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SystemHeader {

    // 시스템 헤더
    private int stdTmsgLen;            //표준전문길이
    private String crypDvCd;           //암호화구분코드
    private String tmsgWrtgDt;         //전문작성일자
    private String tmsgCreSysNm;       //전문생성시스템명
    private String stdTmsgSeqNo;       //표준전문일련번호
    private int stdTmsgPrgrNo;         //표준전문진행번호
    private String ipv6Adr;            //IPV6주소
    private String envrInfoDvCd;       //환경정보 구분코드
    private String fstTrmsSysCd;       //최초전송시스템코드
    private String trmsSysCd;          //전송시스템코드
    private String trmsNdNo;           //전송노드번호
    private String rqstRspsDvCd;       //요청응답구분코드
    private String trscSyncDvCd;       //거래동기화구분코드
    private String tmsgRqstDtm;        //전문요청일시
    private String recvSvcCd;          //수신서비스코드
    private String rsltRecvSvcCd;      //결과수신서비스코드
    private String oliIntfId;          //EAI인터페이스ID
    private String tmsgRspsDtm;        //전문응답일시
    private String procRsltDvCd;       //처리결과구분코드
    private String prnTmsgTypCd;       //출력전문유형코드
    private int tmsgCntnSeqNo;         //전문연속일련번호
    private String dsblSysCd;          //장애시스템코드
    private String stdTmsgErrCd;       //표준전문오류코드
    private String tmsgVerDvCd;        //전문버젼구분코드
    private String cntyCd;             //G국가코드
    private String lnggDvCd;           //언어구분코드
    private String chnlTypCd;          //채널유형코드
    private String chnlNdNo;           //패널노드번호
    private String chnlSessId;         //채널세션ID
    private String domsSessId;         //대내세션ID
    private String otsdSessId;         //대외세션ID
    private String sysRsevStrnCtt;     //시스템예비문자열내용
}