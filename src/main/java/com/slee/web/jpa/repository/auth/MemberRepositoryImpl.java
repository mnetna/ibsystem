package com.slee.web.jpa.repository.auth;

import com.slee.web.jpa.entity.auth.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.slee.web.jpa.entity.auth.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByUserid(String userId) {
        Member fetchOne = queryFactory.selectFrom(member)
                .where(member.userid.eq(userId))
                .fetchFirst();
        return fetchOne != null;
    }

    @Override
    public boolean existsByOrgUserid(String orgUserid) {
        Member fetchOne = queryFactory.selectFrom(member)
                .where(member.orgUserid.eq(orgUserid))
                .fetchFirst();
        return fetchOne != null;
    }
}
