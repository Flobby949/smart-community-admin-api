package com.soft2242.one.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author : Flobby
 * @program : community-admin-api
 * @description : 门禁通行vo
 * @create : 2023-06-23 11:54
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "日通行vo")
public class DoorPassVO implements Comparable<DoorPassVO>{
    @Schema(description = "日期")
    private String date;
    @Schema(description = "开门次数")
    private Integer count;

    @Override
    public int compareTo(@NotNull DoorPassVO o) {
        return Integer.parseInt(this.getDate().substring(8)) - Integer.parseInt(o.getDate().substring(8));
    }
}
