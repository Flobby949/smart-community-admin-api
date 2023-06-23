package com.soft2242.one.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DashboardServiceTest {
    @Resource
    private DashboardService dashboardService;

    @Test
    void getDoorPassList() {
        System.out.println(dashboardService.getDoorPassList());
    }
}