package com.slee.web.common.service;

import com.slee.web.api.exception.ApiException;
import com.slee.web.api.exception.ErrorCode;
import com.slee.web.common.model.auth.MSignupRequestDto;
import com.slee.web.common.model.auth.SignupRequestDto;
import com.slee.web.constant.ApiDefine;
import com.slee.web.jpa.entity.auth.DeviceInfo;
import com.slee.web.jpa.entity.auth.Member;
import com.slee.web.jpa.entity.auth.MemberDetail;
import com.slee.web.jpa.repository.auth.DeviceInfoRepository;
import com.slee.web.jpa.repository.auth.MemberDetailRepository;
import com.slee.web.jpa.repository.auth.MemberRepository;
import com.slee.web.util.SecurityUtil;
import com.slee.web.jpa.entity.auth.DeviceInfoPk;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;
    private final DeviceInfoRepository deviceInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signup(SignupRequestDto signupRequestDto) {
        MemberDetail memberDetail = memberDetailRepository.findByBankInfo(signupRequestDto.getIntrBankId(), ApiDefine.Bank.valueOf(signupRequestDto.getBankCd()));

        // MemberDetail이 존재하지 않으면 validation 체크를 진행하지 않았으므로 에러 리턴
        if (memberDetail == null) {
            throw new ApiException(ErrorCode.ACCOUNT_NOTEXIST_ERROR);
        }

        // RegistYn 값이 "Y"일 경우 이미 회원가입 절차가 끝난 회원이므로 에러 리턴
        if ("Y".equals(memberDetail.getRegistYn())) {
            throw new ApiException(ErrorCode.ACCOUNT_NOTEXIST_ERROR);
        }

        // 회원가입 시 최초 or 은행 추가 버튼이 있어야함
        // orgUserid가 있다면 에러 리턴하고 은행 추가 로직으로 진입해야함
        Optional<Member> optional = memberRepository.findById(SecurityUtil.makeUserid(signupRequestDto.getUserid(), signupRequestDto.getBankCd()));
        if (optional.isPresent()) {
            throw new ApiException(ErrorCode.ACCOUNT_REGISTER_ERROR);
        }

        Member result = memberRepository.save(signupRequestDto.toMember(passwordEncoder));

        // MemberDetail을 등록 완료로 변경
        memberDetail.setRegistYn("Y");
        memberDetailRepository.save(memberDetail);

        return result;
    }

    public String validationCheck(SignupRequestDto signupRequestDto) {
        MemberDetail memberDetail = memberDetailRepository.findByBankInfo(signupRequestDto.getIntrBankId(), ApiDefine.Bank.valueOf(signupRequestDto.getBankCd()));

        // 기존에 등록되어 있고 PW 세팅이 Y로 되어 있다면 유저 등록을 마친 고객이므로 에러 리턴
        if (memberDetail != null && "Y".equals(memberDetail.getRegistYn())) {
            throw new ApiException(ErrorCode.ACCOUNT_REGISTER_ERROR);
        }

        // TODO 1QONCORE Validation 체크 필요!

        return signupRequestDto.getSrchCustNo();
    }

    @Transactional
    public DeviceInfo saveDeviceInfo(MSignupRequestDto mSignupRequestDto) {
        boolean isExistDeviceInfo = deviceInfoRepository.findById(
                DeviceInfoPk.builder()
                        .userid(SecurityUtil.makeUserid(mSignupRequestDto.getUserid(), mSignupRequestDto.getBankCd()))
                        .deviceUuid(mSignupRequestDto.getDeviceUuid())
                        .build())
                .isPresent();
        if (isExistDeviceInfo) {
            throw new ApiException(ErrorCode.DEVICE_EXIST_ERROR);
        }

        DeviceInfo deviceInfo = DeviceInfo.builder()
                .userid(SecurityUtil.makeUserid(mSignupRequestDto.getUserid(), mSignupRequestDto.getBankCd()))
                .deviceUuid(mSignupRequestDto.getDeviceUuid())
                .build();
        return deviceInfoRepository.save(deviceInfo);
    }

    @Transactional
    public Member savePin(MSignupRequestDto mSignupRequestDto) {
        Member member = memberRepository.findById(SecurityUtil.makeUserid(mSignupRequestDto.getUserid(), mSignupRequestDto.getBankCd()))
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOTEXIST_ERROR));
        member.setPin(mSignupRequestDto.toEncryptPin(passwordEncoder));
        return memberRepository.save(member);
    }

//    @Transactional
//    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
//        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
//            throw new ApiException(ErrorCode.REFRESHTOKEN_INVALID_ERROR);
//        }
//        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
//
////        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
////                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
//
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName());
//        Optional.ofNullable(refreshToken)
//                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_LOGOUT_ERROR));
//
//        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
//            throw new ApiException(ErrorCode.REFRESHTOKEN_INVALID_ERROR);
//        }
//        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
//        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
//        refreshTokenRepository.save(newRefreshToken);
//        return tokenDto;
//    }

//    @Transactional
//    public MemberResponseDto logout(MemberRequestDto memberRequestDto) {
//        Member member = memberRepository.findByUser(memberRequestDto.getUserid());
//        Optional.ofNullable(member)
//                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOTEXIST_ERROR));
//
//        RefreshToken refreshToken = refreshTokenRepository.findByKey(memberRequestDto.getUserid());
//        Optional.ofNullable(refreshToken)
//                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_LOGOUT_ERROR));
//
//        refreshTokenRepository.delete(refreshToken);
//
//        return MemberResponseDto.of(member);
//    }
}
