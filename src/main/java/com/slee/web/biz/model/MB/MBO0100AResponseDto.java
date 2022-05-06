package com.slee.web.biz.model.MB;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
public class MBO0100AResponseDto {
	/** 업무구분코드(DP=수신,LN=여신) **/
	private String bizDvCd;
	/** 계좌번호 **/
	private String refNo;
	/** 상품명 **/
	private String prdNm;
	/** 상품통화코드 **/
	private String curCd;
	/** 잔액(수신:잔고, 여신:대출잔액) **/
	private BigDecimal bal;
}
