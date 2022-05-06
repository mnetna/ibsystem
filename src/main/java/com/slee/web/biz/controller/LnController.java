package com.slee.web.biz.controller;

import java.net.URISyntaxException;

import javax.validation.Valid;

import com.slee.web.biz.service.LnService;
import com.slee.web.constant.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slee.web.api.model.common.CommonResponse;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.biz.model.LN.LNO0100ARequestDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ln")
public class LnController {

    private final LnService lnService;

    @PostMapping(value="{target}/LNO0100A")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "JWT Token [Bearer ]", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value="대출정보 조회", notes="대출 상세 정보를 조회한다.")
    public ResponseEntity<?> getLNO0100A(@RequestBody @Valid LNO0100ARequestDto lno0100ARequestDto, @PathVariable(name="target") String target) throws URISyntaxException {
        SystemInfo systemInfo = SystemInfo.builder()
                .scrnId("LN01")
                .subjCd("LN")
                .build();
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(lnService.excuteLNO0100A(lno0100ARequestDto, systemInfo, target)).build();
	        return ResponseEntity.ok(commonResponseDto);
    }

    @PostMapping(value="{target}/LNO0100AT3")
    @ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "JWT Token [Bearer ]", required = true, dataType = "string", paramType = "header")})
    @ApiOperation(value="대출 Schedule 정보 조회", notes="대출 Schedule 정보를 조회한다.")
    public ResponseEntity<?> getLNO0100AT3(@RequestBody @Valid LNO0100ARequestDto lno0100ARequestDto, @PathVariable(name="target") String target) throws URISyntaxException {
        SystemInfo systemInfo = SystemInfo.builder()
                .scrnId("LN01")
                .subjCd("LN")
                .build();
        CommonResponse<Object> commonResponseDto = CommonResponse.builder()
                .status(ResponseStatus.OK)
                .output(lnService.excuteLNO0100AT3(lno0100ARequestDto, systemInfo, target)).build();
        return ResponseEntity.ok(commonResponseDto);
    }
}
