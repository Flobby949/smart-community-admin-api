package com.soft2242.one.dao;


import com.soft2242.one.base.mybatis.dao.BaseDao;
import com.soft2242.one.entity.PatrolPlanEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 巡更计划表
*
* @author litao soft2242@gmail.com
* @since 1.0.0 2023-05-25
*/
@Mapper
public interface PatrolPlanDao extends BaseDao<PatrolPlanEntity> {
	
}