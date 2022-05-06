package com.slee.web.config.auth.userdetails;

import com.slee.web.jpa.entity.auth.Member;
import com.slee.web.jpa.repository.auth.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class BasicUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Member member = memberRepository.findById(userid).orElseThrow(() -> new UsernameNotFoundException(userid + " -> 데이터베이스에서 찾을 수 없습니다."));
        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole().toString());
        return new User(
                String.valueOf(member.getUserid()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}