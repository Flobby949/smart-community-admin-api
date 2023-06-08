package com.soft2242.one.vo;

import com.soft2242.one.base.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
* 电话表
*
* @author xuelong
* @since 1.0.0 2023-06-07
*/
@Data
@Schema(description = "电话表")
public class PhoneVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "社区id")
	private Long communityId;

	@Schema(description = "电话类型(0:物业电话，1：快递电话)")
	private Integer type;

	@Schema(description = "名字")
	private String name;

	@Schema(description = "电话")
	private String phone;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;

	@Schema(description = "创建者")
	private Long creator;

	@Schema(description = "更新者")
	private Long updater;

	@Schema(description = "删除标识（0：未删除 1：已删除）")
	private Integer deleted;


}
