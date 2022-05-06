package com.slee.web.common.model.common;

import com.slee.web.jpa.entity.common.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDto {
    private String menuId;
    private String parent;
    private String menuNm;
    private String appId;
    private int sortSeq;

    public static MenuResponseDto of(Menu menu) {
        return new MenuResponseDto(
                menu.getMenuId()
                , menu.getParent()
                , menu.getMenuNm()
                , menu.getAppId()
                , menu.getSortSeq());
    }
}
