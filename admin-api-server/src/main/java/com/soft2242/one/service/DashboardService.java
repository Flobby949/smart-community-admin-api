package com.soft2242.one.service;

import com.soft2242.one.vo.CommunityPassRecordVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : community-admin-api
 * @description : 仪表盘服务
 * @create : 2023-06-23 11:56
 **/

public interface DashboardService {

    /**
     * 获取门禁通行记录
     * @return vo
     */
    List<CommunityPassRecordVO> getDoorPassList();
}
