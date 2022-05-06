package com.slee.web.biz.model.DP;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DPO0100ARequestDto {
	@ApiModelProperty (example = "20210531")
	private String endDt;
	@ApiModelProperty (example = "01")
	private String inqTyp;
	@ApiModelProperty (example = "000120800093312")
	private String refNo;
	@ApiModelProperty (example = "20210531")
	private String strDt;
}
