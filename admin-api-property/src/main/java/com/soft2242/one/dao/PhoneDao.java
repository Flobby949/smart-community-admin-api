package com.soft2242.one.dao;


import com.soft2242.one.base.mybatis.dao.BaseDao;
import com.soft2242.one.entity.PhoneEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 电话表
*
* @author xuelong
* @since 1.0.0 2023-06-07
*/
@Mapper
public interface PhoneDao extends BaseDao<PhoneEntity> {

}
