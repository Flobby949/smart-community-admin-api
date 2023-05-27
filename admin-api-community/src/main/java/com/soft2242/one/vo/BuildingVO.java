package com.soft2242.one.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.util.Date;

/**
 * @ClassName BuildingVO
 * @Description TODO
 * @Author Dr.king
 * @Date 2023/5/26 08:12
 */
@Data
@Schema(description = "楼宇")
public class BuildingVO {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;
    @Schema(description = "社区ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long communityId;
    @Schema(description = "社区名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String communityName;
    @Schema(description = "楼宇名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String buildingName;
    @Schema(description = "所在单元", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer units;
    @Schema(description = "使用面积", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer usedArea;
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createTime;
    @Schema(description = "楼宇备注", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
}
