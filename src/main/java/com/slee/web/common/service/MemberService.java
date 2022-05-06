package com.slee.web.common.service;

import com.slee.web.common.model.auth.MemberDetailRequestDto;
import com.slee.web.common.model.auth.MemberResponseDto;
import com.slee.web.common.model.auth.SignupRequestDto;
import com.slee.web.api.exception.ApiException;
import com.slee.web.api.exception.ErrorCode;
import com.slee.web.jpa.entity.auth.Member;
import com.slee.web.jpa.entity.auth.MemberDetail;
import com.slee.web.jpa.repository.auth.MemberDetailRepository;
import com.slee.web.jpa.repository.auth.MemberRepository;
import com.slee.web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto getMember() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentUserid())
                .orElseThrow(() -> new ApiException(ErrorCode.ACCOUNT_NOTEXIST_ERROR));
        MemberDetail memberDetail = memberDetailRepository.findByBankInfo(member.getIBankId(), member.getBankCd());
        return MemberResponseDto.of(member, memberDetail);
    }

    public boolean existsByUserid(String userid) {
        return memberRepository.existsByUserid(userid);
    }

    public boolean existsByOrgUserid(String orgUserid) {
        return memberRepository.existsByOrgUserid(orgUserid);
    }

    @Transactional
    public Member saveMember(SignupRequestDto signupRequestDto) {
        boolean isExistMember = memberRepository.findById(SecurityUtil.makeUserid(signupRequestDto.getUserid(), signupRequestDto.getBankCd())).isPresent();
        if (isExistMember) {
            throw new ApiException(ErrorCode.ACCOUNT_REGISTER_ERROR);
        }
        return memberRepository.save(signupRequestDto.toMember(passwordEncoder));
    }

    @Transactional
    public MemberDetail saveMemberDetail(MemberDetailRequestDto memberDetailRequestDto) {
        return memberDetailRepository.save(memberDetailRequestDto.toMemberDetail(false));
    }
}
