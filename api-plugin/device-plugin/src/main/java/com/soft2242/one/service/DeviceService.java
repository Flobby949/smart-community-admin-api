package com.soft2242.one.service;

import com.soft2242.one.entity.DeviceInfoDTO;

/**
 * @author : Flobby
 * @program : community-client-api
 * @description : 设备操作服务
 * @create : 2023-06-06 08:37
 **/

public interface DeviceService {

    Boolean addOrUpdateDevice(DeviceInfoDTO deviceInfoDTO);
}
