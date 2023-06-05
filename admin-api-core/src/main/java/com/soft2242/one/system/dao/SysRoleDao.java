package com.soft2242.one.system.dao;

import com.soft2242.one.base.mybatis.dao.BaseDao;
import com.soft2242.one.system.entity.SysDepartmentEntity;
import com.soft2242.one.system.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 根据用户ID，获取用户最大的数据范围
     */
    Integer getDataScopeByUserId(@Param("userId") Long userId);

    List<SysRoleEntity> getList(Map<String, Object> params);
}
