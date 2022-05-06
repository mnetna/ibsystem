package com.slee.web.jpa.repository.auth;

import com.slee.web.jpa.entity.auth.RefreshToken;

public interface RefreshTokenRepositoryCustom {
    RefreshToken findByKey(String key);
}
