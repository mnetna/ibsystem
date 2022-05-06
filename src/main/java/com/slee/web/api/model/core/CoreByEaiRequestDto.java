package com.slee.web.api.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoreByEaiRequestDto {
    @JsonProperty("input")
    private Object input;
    @JsonProperty("SysInfo")
    private SystemInfo sysInfo;
    @JsonProperty("apiServiceCode")
    private String apiServiceCode;
}
