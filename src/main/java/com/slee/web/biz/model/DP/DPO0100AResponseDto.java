package com.slee.web.biz.model.DP;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
public class DPO0100AResponseDto {
	private String custNo;
	private String custNm;
	private String actDvCd;
	private String oldAcctNo;
	private String curCd;
	private String subjCd;
	private String prdNm;
	private String cmNmlRelCd;
	private String cmNmlCustNo;
	private String cmNmlCustNm;
	private String acctNm;
	private String intPaymTypCd;
	private String irrtDvCd;
	private BigDecimal bascIrrt;
	private BigDecimal negoIrrt;
	private String taxtCd;
	private BigDecimal bal;
	private BigDecimal paymStopA;
	private BigDecimal minBal;
	private BigDecimal pyblAmt;
	private String openDt;
	private String lstIntCalcDt;
	private String rllvDt;
	private Long ContMmsCnt;
	private Long ContDdCnt;
	private String expiDt;
	private String lstTrscDt;
	private String pstpnExpiDt;
}
