package com.slee.web.api.model.common;

import com.slee.web.constant.ResponseStatus;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private ResponseStatus status;
    private String message;
    private T output;
}
