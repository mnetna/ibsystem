package com.slee.web.biz.controller;

import com.slee.web.biz.model.DP.DPO0100ARequestDto;
import com.slee.web.biz.service.DpService;
import com.slee.web.constant.ResponseStatus;
import com.slee.web.api.model.common.CommonResponse;
import com.slee.web.api.model.core.SystemInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dp")
public class DpController {
	private final DpService dpService;

	@PostMapping(value="{target}/DPO0100A")
	@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "JWT Token [Bearer ]", required = true, dataType = "string", paramType = "header")})
	@ApiOperation(value="계좌정보 조회", notes="계좌 상세 정보를 조회한다.")
	public ResponseEntity<?> getDPO0100A(@RequestBody @Valid DPO0100ARequestDto dpo0100ARequestDto, @PathVariable(name="target") String target) throws URISyntaxException {
		SystemInfo systemInfo = SystemInfo.builder()
				.scrnId("DP01")
				.subjCd("DP")
				.build();
		CommonResponse<Object> commonResponseDto = CommonResponse.builder()
//				.status(ResponseStatus.OK)
				.output(dpService.excuteDPO0100A(dpo0100ARequestDto, systemInfo, target)).build();
		return ResponseEntity.ok(commonResponseDto);
	}

	@PostMapping(value="{target}/DPO0100A06")
	@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "JWT Token [Bearer ]", required = true, dataType = "string", paramType = "header")})
	@ApiOperation(value="적금 거래내역 조회", notes="적금 거래내역 정보를 조회한다.")
	public ResponseEntity<?> getDPO0100A06(@RequestBody @Valid DPO0100ARequestDto dpo0100ARequestDto, @PathVariable(name="target") String target) throws URISyntaxException {
		SystemInfo systemInfo = SystemInfo.builder()
				.scrnId("DP01")
				.subjCd("DP")
				.build();
		CommonResponse<Object> commonResponseDto = CommonResponse.builder()
				.status(ResponseStatus.OK)
				.output(dpService.excuteDPO0100A06(dpo0100ARequestDto, systemInfo, target)).build();
		return ResponseEntity.ok(commonResponseDto);
	}
}