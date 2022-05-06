package com.slee.web.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /** System **/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0001"),
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002"),

    /** Security **/
    ACCESSTOKEN_NOTEXIST_AUTHORITY_ERROR(HttpStatus.UNAUTHORIZED, "S0001", "권한 정보가 없는 토큰입니다."),
    REFRESHTOKEN_INVALID_ERROR(HttpStatus.UNAUTHORIZED, "S0002", "Refresh Token 이 유효하지 않습니다."),
    AUTHENTICATION_NOTEXIST_ERROR(HttpStatus.UNAUTHORIZED, "S0003", "Context 인증 정보가 존재하지 않습니다."),

    /** Account **/
    ACCOUNT_REGISTER_ERROR(HttpStatus.UNAUTHORIZED, "A0001", "이미 가입되어 있는 사용자입니다."),
    ACCOUNT_INVALID_ERROR(HttpStatus.UNAUTHORIZED, "A0002", "유저명 혹은 패스워드가 일치하지 않습니다."),
    ACCOUNT_NOTEXIST_ERROR(HttpStatus.UNAUTHORIZED, "A0003", "사용자 정보가 존재하지 않습니다."),
    ACCOUNT_LOGOUT_ERROR(HttpStatus.UNAUTHORIZED, "A0004", "로그아웃한 사용자입니다."),
    DEVICE_EXIST_ERROR(HttpStatus.UNAUTHORIZED, "A0005", "이미 존재하는 디바이스입니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ErrorCode(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
