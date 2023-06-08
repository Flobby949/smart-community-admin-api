package com.soft2242.one.service;


import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.mybatis.service.BaseService;
import com.soft2242.one.entity.PhoneEntity;
import com.soft2242.one.query.PhoneQuery;
import com.soft2242.one.vo.PhoneVO;

import java.util.List;

/**
 * 电话表
 *
 * @author xuelong
 * @since 1.0.0 2023-06-07
 */
public interface PhoneService extends BaseService<PhoneEntity> {

    PageResult<PhoneVO> page(PhoneQuery query);

    void save(PhoneVO vo);

    void update(PhoneVO vo);

    void delete(List<Long> idList);
}
