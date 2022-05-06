package com.slee.web.jpa.repository.auth;

import com.slee.web.jpa.entity.auth.RefreshToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.slee.web.jpa.entity.auth.QRefreshToken.refreshToken;

@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public RefreshToken findByKey(String key) {
        return queryFactory.selectFrom(refreshToken)
                .where(refreshToken.key.eq(key))
                .fetchOne();
    }
}
