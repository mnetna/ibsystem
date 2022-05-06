package com.slee.web.jpa.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoPk implements Serializable {
    @Id
    @Column
    private String userid;

    @Id
    @Column
    private String deviceUuid;
}
