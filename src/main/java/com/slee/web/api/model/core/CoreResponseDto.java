package com.slee.web.api.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoreResponseDto {
    @JsonProperty("output")
    private Map<String, Object> output;
    @JsonProperty("SysInfo")
    private SystemInfo systemInfo;
    @JsonProperty("GlobalID")
    private String globalId;
}
