package com.slee.web.jpa.repository.auth;

import com.slee.web.jpa.entity.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>, RefreshTokenRepositoryCustom {
//    Optional<RefreshToken> findByKey(String key);
}