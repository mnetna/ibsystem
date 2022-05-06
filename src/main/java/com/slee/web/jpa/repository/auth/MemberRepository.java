package com.slee.web.jpa.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slee.web.jpa.entity.auth.Member;

public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom {
//    Optional<Member> findByUsername(String username);
//    boolean existsByUsername(String username);
}
