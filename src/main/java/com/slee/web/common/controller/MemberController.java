package com.slee.web.common.controller;

import com.slee.web.api.model.common.CommonResponse;
import com.slee.web.common.service.MemberService;
import com.slee.web.constant.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/getMember")
    @ApiIgnore
    public ResponseEntity<CommonResponse<Object>> getMember() {
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(memberService.getMember()).build();
        return ResponseEntity.ok(commonResponse);
    }
}
