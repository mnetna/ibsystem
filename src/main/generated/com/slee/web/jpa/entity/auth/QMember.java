package com.slee.web.jpa.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.slee.web.constant.ApiDefine;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1831398858L;

    public static final QMember member = new QMember("member1");

    public final EnumPath<ApiDefine.Bank> bankCd = createEnum("bankCd", ApiDefine.Bank.class);

    public final StringPath iBankId = createString("iBankId");

    public final StringPath lnggDvCd = createString("lnggDvCd");

    public final StringPath orgUserid = createString("orgUserid");

    public final StringPath password = createString("password");

    public final StringPath pin = createString("pin");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath userid = createString("userid");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

