package com.slee.web.biz.model.MB;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class MBO0200ARequestDto {
	@ApiModelProperty(example = "000122000117731")
	/** 계좌번호 **/
	private String refNo;
}
