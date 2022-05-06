package com.slee.web.jpa.repository.auth;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slee.web.constant.ApiDefine;
import com.slee.web.jpa.entity.auth.MemberDetail;
import lombok.RequiredArgsConstructor;

import static com.slee.web.jpa.entity.auth.QMemberDetail.memberDetail;

@RequiredArgsConstructor
public class MemberDetailRepositoryImpl implements MemberDetailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public MemberDetail findByBankInfo(String iBankId, ApiDefine.Bank bank) {
        return queryFactory.selectFrom(memberDetail)
                .where(memberDetail.iBankId.eq(iBankId))
                .where(memberDetail.bankCd.eq(bank))
                .fetchOne();
    }
}
