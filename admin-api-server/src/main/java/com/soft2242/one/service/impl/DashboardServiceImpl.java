package com.soft2242.one.service.impl;

import com.soft2242.one.base.common.utils.DateUtils;
import com.soft2242.one.service.DashboardService;
import com.soft2242.one.service.PassRecordService;
import com.soft2242.one.vo.CommunityPassRecordVO;
import com.soft2242.one.vo.DoorPassVO;
import com.soft2242.one.vo.PassRecordVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Flobby
 * @program : community-admin-api
 * @description :
 * @create : 2023-06-23 11:56
 **/

@Service
@Slf4j
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private PassRecordService passRecordService;

    @Override
    public List<CommunityPassRecordVO> getDoorPassList() {
        List<CommunityPassRecordVO> resultList = new ArrayList<>();
        Map<String, Map<String, List<PassRecordVO>>> collect = passRecordService.allRecord().stream()
                .collect(Collectors.groupingBy(PassRecordVO::getCommunityName,
                        Collectors.groupingBy(item -> DateUtils.format(item.getCreateTime()))));
        log.info(collect.toString());
        collect.entrySet().stream().forEach(entry -> {
            List<DoorPassVO> doorPassVOList = new ArrayList<>();
            entry.getValue().entrySet().stream().forEach(valueEntry -> {
                doorPassVOList.add(DoorPassVO.builder()
                                .date(valueEntry.getKey())
                                .count(valueEntry.getValue().size())
                        .build());
            });
            Collections.sort(doorPassVOList);
            CommunityPassRecordVO vo = new CommunityPassRecordVO();
            vo.setCommunity(entry.getKey());
            vo.setPassRecordList(doorPassVOList);
            resultList.add(vo);
        });
        return resultList;
    }
}
