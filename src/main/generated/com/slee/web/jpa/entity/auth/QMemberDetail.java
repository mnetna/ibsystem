package com.slee.web.jpa.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.slee.web.constant.ApiDefine;


/**
 * QMemberDetail is a Querydsl query type for MemberDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberDetail extends EntityPathBase<MemberDetail> {

    private static final long serialVersionUID = -1474226649L;

    public static final QMemberDetail memberDetail = new QMemberDetail("memberDetail");

    public final StringPath adrTypCd = createString("adrTypCd");

    public final EnumPath<ApiDefine.Bank> bankCd = createEnum("bankCd", ApiDefine.Bank.class);

    public final StringPath baseAdr = createString("baseAdr");

    public final StringPath btdy = createString("btdy");

    public final StringPath cntyCd = createString("cntyCd");

    public final StringPath custDvCd = createString("custDvCd");

    public final StringPath custEngNm = createString("custEngNm");

    public final StringPath custInfoKindCd = createString("custInfoKindCd");

    public final StringPath custNm = createString("custNm");

    public final StringPath custNo = createString("custNo");

    public final StringPath emalAdr = createString("emalAdr");

    public final StringPath gndrDvCd = createString("gndrDvCd");

    public final StringPath iBankId = createString("iBankId");

    public final StringPath mngBrNo = createString("mngBrNo");

    public final StringPath registYn = createString("registYn");

    public final StringPath zipNo = createString("zipNo");

    public QMemberDetail(String variable) {
        super(MemberDetail.class, forVariable(variable));
    }

    public QMemberDetail(Path<? extends MemberDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberDetail(PathMetadata metadata) {
        super(MemberDetail.class, metadata);
    }

}

