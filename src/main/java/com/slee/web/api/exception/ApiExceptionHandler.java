package com.slee.web.api.exception;

import com.slee.web.api.model.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final ApiException e) {
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getError().getMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
        log.error("There was an RuntimeException during API request =>", e);
        return ResponseEntity
                .status(ErrorCode.RUNTIME_EXCEPTION.getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(ErrorCode.RUNTIME_EXCEPTION.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final Exception e) {
        log.error("There was an Unknown Exception during API request =>", e);
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }
}
