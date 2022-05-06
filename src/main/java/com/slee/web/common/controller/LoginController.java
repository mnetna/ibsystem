package com.slee.web.common.controller;

import com.slee.web.api.model.common.CommonResponse;
import com.slee.web.common.service.LoginService;
import com.slee.web.constant.ResponseStatus;
import com.slee.web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/basic/token")
    public ResponseEntity<CommonResponse<Object>> basicLogin() {
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(loginService.login())
                .build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping("/basic/auth")
    public ResponseEntity<CommonResponse<Object>> isAuth() {
        if(SecurityUtil.isAuthentication()) {
            return ResponseEntity.ok(CommonResponse.builder().status(ResponseStatus.OK).build());
        } else {
            return ResponseEntity.ok(CommonResponse.builder().status(ResponseStatus.FAIL).build());
        }
    }

    @PostMapping("/pin/token")
    public ResponseEntity<CommonResponse<Object>> pinLogin() {
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(loginService.login())
                .build();
        return ResponseEntity.ok(commonResponseDto);
    }
}
