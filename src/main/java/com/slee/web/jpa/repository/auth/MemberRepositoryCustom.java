package com.slee.web.jpa.repository.auth;

public interface MemberRepositoryCustom {
    boolean existsByUserid(String userId);
    boolean existsByOrgUserid(String userId);
}
