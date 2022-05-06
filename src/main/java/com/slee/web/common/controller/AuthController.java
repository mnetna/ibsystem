package com.slee.web.common.controller;

import com.slee.web.api.model.common.CommonResponse;
import com.slee.web.api.model.core.SystemInfo;
import com.slee.web.biz.model.CU.CUO0100ARequestDto;
import com.slee.web.biz.model.CU.CUO0100AResponseDto;
import com.slee.web.biz.model.CU.CugMgntCustCtfcIOGrid01;
import com.slee.web.biz.service.CuService;
import com.slee.web.common.model.auth.MSignupRequestDto;
import com.slee.web.common.model.auth.MemberDetailRequestDto;
import com.slee.web.common.model.auth.SignupRequestDto;
import com.slee.web.common.service.MemberService;
import com.slee.web.constant.CustomerDefine;
import com.slee.web.constant.ResponseStatus;
import com.slee.web.jpa.entity.auth.DeviceInfo;
import com.slee.web.jpa.entity.auth.Member;
import com.slee.web.jpa.entity.auth.MemberDetail;
import com.slee.web.util.SecurityUtil;
import com.slee.web.common.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;
    private final CuService cuService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<Object>> signup(@NotNull @RequestBody SignupRequestDto signupRequestDto) {
        Member member = authService.signup(signupRequestDto);
        if (member == null) {
            return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.FAIL).message("유저 등록에 실패하였습니다.").build());
        }
        return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.OK).build());
    }

    @PostMapping("{target}/signup/validation")
    public ResponseEntity<CommonResponse<Object>> validation(@RequestBody SignupRequestDto signupRequestDto, @PathVariable(name="target") String target) throws Exception {
        // 유저 등록 전 validation 체크
        String customerNo = authService.validationCheck(signupRequestDto);

        // 유저 정보 조회
        CUO0100AResponseDto responseDto = cuService.getCUO0100A(
                CUO0100ARequestDto.builder()
                        .srchCustNo(customerNo)
                        .build(),
                SystemInfo.builder()
                        .scrnId("CU01")
                        .subjCd("CU")
                        .build(),
                target,
                signupRequestDto.getBankCd());

        // 조회한 유저 정보로 MemberDto 생성
        // Base 정보 우선 생성
        MemberDetailRequestDto memberDetailRequestDto = MemberDetailRequestDto.builder()
                        .iBankId(signupRequestDto.getIntrBankId())
                        .bankCd(signupRequestDto.getBankCd())
                        .custNo(responseDto.getCustNo())
                        .custDvCd(responseDto.getCustDvCd())
                        .custInfoKindCd(responseDto.getCustInfoKindCd())
                        .mngBrNo(responseDto.getMngBrNo())
                        .custNm(responseDto.getCustNm())
                        .custEngNm(responseDto.getCustEngNm())
                        .cntyCd(responseDto.getCntyCd())
                        .build();
        
        // Address 및 Tell 정보 생성
        for (CugMgntCustCtfcIOGrid01 cugMgntCustCtfcIOGrid01 : responseDto.getGrid01()) {
            if ("N".equals(cugMgntCustCtfcIOGrid01.getMainYn())) {
                continue;
            }
            memberDetailRequestDto.setEmalAdr(cugMgntCustCtfcIOGrid01.getEmalAdr());
            memberDetailRequestDto.setAdrTypCd(cugMgntCustCtfcIOGrid01.getAdrTypCd());
            memberDetailRequestDto.setBaseAdr(cugMgntCustCtfcIOGrid01.getBaseAdr());
            memberDetailRequestDto.setZipNo(cugMgntCustCtfcIOGrid01.getZipNo());
        }

        CustomerDefine.CustTyp custTyp = CustomerDefine.CustTyp.findByValue(responseDto.getCustTyp());
        
        // 개인회원 정보 생성
        if (custTyp.equals(CustomerDefine.CustTyp.INDIVIDUAL)) {
            memberDetailRequestDto.setGndrDvCd(responseDto.getGndrDvCd());
            memberDetailRequestDto.setBtdy(responseDto.getBtdy());
        }

        // MemberDetail 테이블에 유저 상세 정보 저장
        MemberDetail memberDetail = memberService.saveMemberDetail(memberDetailRequestDto);
        if (memberDetail == null) {
            return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.FAIL).message("인터넷 뱅킹 유저 확인에 실패하였습니다.").build());
        }
        return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.OK).build());
    }

    @PostMapping("/signup/duplication")
    public ResponseEntity<CommonResponse<Object>> duplicationCheck(@RequestBody SignupRequestDto signupRequestDto) {
        boolean isSuccess = memberService.existsByOrgUserid(SecurityUtil.getUserid(signupRequestDto.getUserid()));
        if (!isSuccess) {
            return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.FAIL).message("아이디가 중복되어 사용할 수 없습니다.").build());
        }
        return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.OK).build());
    }

    @PostMapping("/signup/default")
    public ResponseEntity<CommonResponse<Object>> defaultSignup(@RequestBody SignupRequestDto signupRequestDto) {
        Member member = memberService.saveMember(signupRequestDto);
        if (member == null) {
            return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.FAIL).message("회원가입에 실패하였습니다.").build());
        }
        return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.OK).build());
    }

    @PostMapping("/signup/m")
    @Transactional
    public ResponseEntity<CommonResponse<Object>> mSignup(@RequestBody MSignupRequestDto mSignupRequestDto) {
        // 디바이스 정보를 저장
        DeviceInfo deviceInfo = authService.saveDeviceInfo(mSignupRequestDto);
        if (deviceInfo == null) {
            return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.FAIL).message("디바이스 정보 저장에 실패하였습니다.").build());
        }
        // PIN 정보를 저장
        Member member = authService.savePin(mSignupRequestDto);
        if(member == null) {
            return ResponseEntity.ok(CommonResponse.builder().status(com.slee.web.constant.ResponseStatus.FAIL).message("모바일 유저 등록에 실패하였습니다.").build());
        }
        return ResponseEntity.ok(CommonResponse.builder().status(ResponseStatus.OK).build());
    }

//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<MemberResponseDto> logout(@RequestBody MemberRequestDto memberRequestDto) {
//        return ResponseEntity.ok(authService.logout(memberRequestDto));
//    }
}
