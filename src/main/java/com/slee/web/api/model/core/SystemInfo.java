package com.slee.web.api.model.core;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor
public class SystemInfo {
    private String instCd;
    private String acBrNo;
    private String baseCurCd;
    private String cntyCd;
    private String dtFrmt;
    private String dtMarkFrmt;
    private String globId;
    private String istBrNo;
    private String logLev;
    private String rsprApvAlwnYn;
    private String rsprApvYn;
    private String rsprTrscGlobId;
    private String rsprMngrId;
    private String scrnId;
    private String subjCd;
    private String sysDvCd;
    private String sysLnggCd;
    private String tmlNo;
    private String trscDt;
    private String bussDt;
    private String usrNm ;
    private String rcknDt;
    private String trscCd;
    private String procSvcCd;
    private String wsDemnIpAdr;
    private String wsDemnPort;
    private String ipAddr;
    private String lnkIndvCanYn;
    private String rsprApvCreYn;
    private String lnggCd;
    private String certsNo;
    private String testGlobId;
    private String pwStCd;
    private String bussBrUpdAlwnYn;
    private String smltnTrscYn;
    private String scrnMsgPrnYn;
    private String rsprApvDvCd;
    private String trscBrNo;
    private String chnlTypCd;
    private String userId;

    @Builder
    public SystemInfo(String scrnId, String subjCd) {
        this.scrnId = scrnId;
        this.subjCd = subjCd;
        this.trscBrNo = SystemInfoConstants.SYSINFO_TRSC_BR_NO;
        this.chnlTypCd = SystemInfoConstants.SYSINFO_CHNL_TYPE_CD_ONL;
        this.userId = SystemInfoConstants.SYSINFO_USER_ID_GW;
        this.trscDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
