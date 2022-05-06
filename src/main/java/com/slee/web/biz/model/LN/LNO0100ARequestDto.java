package com.slee.web.biz.model.LN;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class LNO0100ARequestDto {
    private String currentPageIndex;
    @ApiModelProperty(example = "T1")
    private String inqTyp;
    private String pageRowCount;
    @ApiModelProperty (example = "000122000117731")
    private String refNo;
    private String totalRowCount;
}
