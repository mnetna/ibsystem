package com.slee.web.jpa.repository.auth;

import com.slee.web.jpa.entity.auth.MemberDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, String>, MemberDetailRepositoryCustom {
}
