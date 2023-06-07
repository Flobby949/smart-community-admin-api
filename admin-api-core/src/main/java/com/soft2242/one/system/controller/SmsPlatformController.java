package com.soft2242.one.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2242.one.base.common.utils.Result;
import com.soft2242.one.system.dao.SmsPlatformDao;
import com.soft2242.one.system.entity.SmsPlatformEntity;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author : Ahang
 * @program : community-admin-api
 * @description :
 * @create : 2023-06-07 15:12
 **/
@RestController
@RequestMapping("/sms/platform")
public class SmsPlatformController {
    @Resource
    private SmsPlatformDao smsPlatformDao;

    @GetMapping("/list")
    public Result list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        Page<SmsPlatformEntity> entityPage = new Page<>(page, limit);
        IPage<SmsPlatformEntity> list = smsPlatformDao.selectPage(entityPage, null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", list.getTotal());
        map.put("items", list.getRecords());

        return Result.ok(map);
    }
}
