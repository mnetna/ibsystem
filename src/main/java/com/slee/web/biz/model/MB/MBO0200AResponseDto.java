package com.slee.web.biz.model.MB;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
public class MBO0200AResponseDto {
	/** 상품명 **/
	private String prdNm;
	/** 대출 금액 **/
	private BigDecimal lonAmt;
	/** 이자배분방법코드 **/
	private String rpayCycDvCd;
	/** 적용 이율 **/
	private BigDecimal apclIrrt;
	/** 상환방법코드 **/
	private String rpayMethCd;
	/** 상환기간개월수 **/
	private Long rpayTrmMmsCnt;
	/** 총대출 만기일자 **/
	private String lonExpiDt;
	/** 상환계좌번호 **/
	private String rpayAcctNo;
	/** 대출잔액 **/
	private BigDecimal lonBal;
	/** 신규일자 **/
	private String newDt;
}
