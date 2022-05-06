package com.slee.web.common.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto {
    private String lnggDvCd;
    private String parent;
}
