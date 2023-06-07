package com.soft2242.one.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.soft2242.one.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信平台
 *
 * @author moqi
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sms_phone_log")
public class SmsPlatformEntity extends BaseEntity {
    /**
     * 平台类型  0：阿里云   1：腾讯云   2：七牛云    3：华为云
     */
    private Integer platform;

    /**
     * 短信签名
     */
    private String template;

    /**
     * 短信模板
     */
    private String deviceToken;


    private String phone;

    private String address;

    private String ip;


    /**
     * 状态  0：禁用   1：启用
     */
    private Integer status;

}