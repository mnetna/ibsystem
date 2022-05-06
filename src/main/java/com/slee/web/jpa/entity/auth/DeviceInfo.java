package com.slee.web.jpa.entity.auth;

import lombok.*;

import javax.persistence.*;

@IdClass(DeviceInfoPk.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "device_info")
@Entity
public class DeviceInfo {

    /** 은행 별 사용자 ID **/
    @Id
    @Column
    private String userid;

    /** 모바일 디바이스 UUID **/
    @Id
    @Column
    private String deviceUuid;
}
