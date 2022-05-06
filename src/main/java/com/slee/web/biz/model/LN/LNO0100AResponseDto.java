package com.slee.web.biz.model.LN;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class LNO0100AResponseDto {
    private String prdNm;
    private BigDecimal fstLonAmt;
    private String prinRpayCycTypCd;
    private BigDecimal apclIrt;
    private String rpayMethCd;
    private Long rpayTrmMmsCnt;
    private String totlLonExpiDt;
    private String depoAcctNo;
}
