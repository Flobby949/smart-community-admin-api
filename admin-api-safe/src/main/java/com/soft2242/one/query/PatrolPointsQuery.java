package com.soft2242.one.query;

import com.soft2242.one.base.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
* 巡更点表查询
*
* @author 软件2242 soft2242@gmail.com
* @since 1.0.0 2023-05-25
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "巡更点表查询")
public class PatrolPointsQuery extends Query {
//    @Schema(description = "所属小区id（t_communtiy）")
//    private Long communityId;
    @Schema(description = "所属小区id")
    private List<Long> communityIds;



}