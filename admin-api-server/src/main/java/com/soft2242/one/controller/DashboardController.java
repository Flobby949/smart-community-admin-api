package com.soft2242.one.controller;

import com.soft2242.one.base.common.utils.Result;
import com.soft2242.one.service.DashboardService;
import com.soft2242.one.vo.CommunityPassRecordVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Flobby
 * @program : community-admin-api
 * @description : 首页仪表板接口
 * @create : 2023-06-23 11:53
 **/

@RestController
@AllArgsConstructor
@RequestMapping("dashboard")
@Tag(name="首页仪表盘")
public class DashboardController {
    private DashboardService dashboardService;

    @GetMapping("passRecord")
    public Result<List<CommunityPassRecordVO>> getPassRecord() {
        return Result.ok(dashboardService.getDoorPassList());
    }
}
