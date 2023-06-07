package com.soft2242.one.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/6/26
 * @description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {
    private String deviceName;
    private String deviceSerial;
    private String model;
    private Integer isEnable;
    private Integer status;
    private Integer exceptionStatus;
    private Double electric;
    private Double power;
    private Long time;
    private Long onOfflineDateTime;
}
