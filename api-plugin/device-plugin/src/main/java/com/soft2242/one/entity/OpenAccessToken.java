package com.soft2242.one.entity;

import lombok.Data;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/6/26
 * @description :
 */
@Data
public class OpenAccessToken {
    private String accessToken;
    private Long expireTime;
}
