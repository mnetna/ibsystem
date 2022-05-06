package com.slee.web.biz.controller;

import java.net.URISyntaxException;

import javax.validation.Valid;

import com.slee.web.biz.service.MbService;
import com.slee.web.common.service.MemberService;
import com.slee.web.constant.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slee.web.api.model.common.CommonResponse;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.biz.model.MB.MBO0100ARequestDto;
import com.slee.web.biz.model.MB.MBO0200ARequestDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mb")
public class MbController {
    private final MbService mbService;
    private final MemberService memberService;

    @PostMapping(value="{target}/MBO0100A")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "JWT Token [Bearer ]", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value="계좌목록 조회", notes="로그인한 유저의 전체 계좌목록을 조회한다.")
    public ResponseEntity<?> getMBO0100A(@RequestBody @Valid MBO0100ARequestDto mbo0100ARequestDto, @PathVariable(name="target") String target) throws URISyntaxException {
        SystemInfo systemInfo = SystemInfo.builder()
                .scrnId("MB01")
                .subjCd("MB")
                .build();
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(mbService.executeMBO0100A(mbo0100ARequestDto, systemInfo, target)).build();
        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping(value="{target}/MBO0200A")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "JWT Token [Bearer ]", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value="여신 정보 상세 조회", notes="여신 정보 상세 조회.")
    public ResponseEntity<?> getMBO0200A(@RequestBody @Valid MBO0200ARequestDto mbo0200ARequestDto, @PathVariable(name="target") String target) throws URISyntaxException {
        SystemInfo systemInfo = SystemInfo.builder()
                .scrnId("MB02")
                .subjCd("MB")
                .build();
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(mbService.executeMBO0200A(mbo0200ARequestDto, systemInfo, target)).build();
        return ResponseEntity.ok(commonResponseDto);
    }
}
