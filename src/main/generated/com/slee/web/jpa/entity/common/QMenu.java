package com.slee.web.jpa.entity.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -1608641512L;

    public static final QMenu menu = new QMenu("menu");

    public final StringPath appId = createString("appId");

    public final StringPath lnggDvCd = createString("lnggDvCd");

    public final StringPath menuId = createString("menuId");

    public final StringPath menuNm = createString("menuNm");

    public final StringPath parent = createString("parent");

    public final NumberPath<Integer> sortSeq = createNumber("sortSeq", Integer.class);

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

