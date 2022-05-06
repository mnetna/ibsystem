package com.slee.web.jpa.entity.auth;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceInfo is a Querydsl query type for DeviceInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceInfo extends EntityPathBase<DeviceInfo> {

    private static final long serialVersionUID = -828754496L;

    public static final QDeviceInfo deviceInfo = new QDeviceInfo("deviceInfo");

    public final StringPath deviceUuid = createString("deviceUuid");

    public final StringPath userid = createString("userid");

    public QDeviceInfo(String variable) {
        super(DeviceInfo.class, forVariable(variable));
    }

    public QDeviceInfo(Path<? extends DeviceInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceInfo(PathMetadata metadata) {
        super(DeviceInfo.class, metadata);
    }

}

