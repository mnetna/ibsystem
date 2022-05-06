package com.slee.web.jpa.repository.common;

import com.slee.web.jpa.entity.common.Menu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slee.web.jpa.entity.common.QMenu;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findAllByLnggDvCd(String lnggDvCd) {
        return queryFactory.selectFrom(QMenu.menu)
                .where(QMenu.menu.lnggDvCd.eq(lnggDvCd))
                .fetch();
    }

    @Override
    public List<Menu> findByParent(String lnggDvCd, String parent) {
        return queryFactory.selectFrom(QMenu.menu)
                .where(QMenu.menu.lnggDvCd.eq(lnggDvCd))
                .where(QMenu.menu.parent.eq(parent))
                .fetch();
    }
}
