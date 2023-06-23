package com.soft2242.one.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : Flobby
 * @program : community-admin-api
 * @description :
 * @create : 2023-06-23 12:08
 **/

@Data
@Schema(description = "小区通行vo")
public class CommunityPassRecordVO {
    @Schema(description = "小区名")
    private String community;
    @Schema(description = "通行记录")
    private List<DoorPassVO> passRecordList;
}
