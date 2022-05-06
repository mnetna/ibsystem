package com.slee.web.biz.model.DP;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class DPO0100A06ResponseDto {
	private String trscDt;
	private String trscTm;
	private String procNm;
	private String rcvPaymDvNm;
	private String trscCurCd;
	private BigDecimal trscAmt;
	private BigDecimal trscAfBal;
	private String rmk;
}