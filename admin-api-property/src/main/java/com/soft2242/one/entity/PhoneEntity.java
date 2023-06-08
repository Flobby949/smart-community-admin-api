package com.soft2242.one.entity;

import com.soft2242.one.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;


/**
 * 电话表
 *
 * @author xuelong
 * @since 1.0.0 2023-06-07
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_phone")
public class PhoneEntity extends BaseEntity {

    /**
     * 社区id
     */
    private Long communityId;

    /**
     * 电话类型(0:物业电话，1：快递电话)
     */
    private Integer type;

    /**
     * 名字
     */
    private String name;

    /**
     * 电话
     */
    private String phone;


}
