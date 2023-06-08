package com.soft2242.one.query;

import com.soft2242.one.base.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 电话表查询
 *
 * @author xuelong
 * @since 1.0.0 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "电话表查询")
public class PhoneQuery extends Query {
    @Schema(description = "社区id")
    private String communityId;

    @Schema(description = "电话类型(0:物业电话，1：快递电话)")
    private String type;

    @Schema(description = "名字")
    private String name;

    @Schema(description = "电话")
    private String phone;

}
