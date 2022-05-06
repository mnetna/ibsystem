package com.slee.web.common.service;

import java.util.List;
import java.util.stream.Collectors;

import com.slee.web.common.model.common.MenuResponseDto;
import com.slee.web.jpa.entity.common.Menu;
import com.slee.web.jpa.repository.common.MenuRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final MenuRepository menuRepository;

    public List<MenuResponseDto> findAllByLnggDvCd(String lnggDvCd) {
        List<Menu> menuList = menuRepository.findAllByLnggDvCd(lnggDvCd);
        return menuList.stream()
                .map(x -> MenuResponseDto.of(x))
                .collect(Collectors.toList());
    }

    public List<MenuResponseDto> findByParent(String lnggDvCd, String parent) {
        List<Menu> menuList = menuRepository.findByParent(lnggDvCd, parent);
        return menuList.stream()
                .map(x -> MenuResponseDto.of(x))
                .collect(Collectors.toList());
    }
}
