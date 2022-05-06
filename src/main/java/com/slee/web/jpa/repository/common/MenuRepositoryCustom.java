package com.slee.web.jpa.repository.common;

import com.slee.web.jpa.entity.common.Menu;

import java.util.List;

public interface MenuRepositoryCustom {
    List<Menu> findAllByLnggDvCd(String lnggDvCd);
    List<Menu> findByParent(String lnggDvCd, String parent);
}
