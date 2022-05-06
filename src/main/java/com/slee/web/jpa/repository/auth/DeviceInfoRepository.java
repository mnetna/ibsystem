package com.slee.web.jpa.repository.auth;

import com.slee.web.jpa.entity.auth.DeviceInfo;
import com.slee.web.jpa.entity.auth.DeviceInfoPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, DeviceInfoPk> {
}
