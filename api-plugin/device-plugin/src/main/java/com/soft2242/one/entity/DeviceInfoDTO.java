package com.soft2242.one.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/5/26
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInfoDTO {
    private String deviceName;
    private String imageUrl;
    private Integer type;
    private String serialNumber;
    private String validateCode;
}