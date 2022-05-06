package com.slee.web.jpa.repository.common;

import com.slee.web.jpa.entity.common.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, MenuRepositoryCustom {
}
