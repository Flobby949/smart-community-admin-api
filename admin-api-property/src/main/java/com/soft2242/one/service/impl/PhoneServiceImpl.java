package com.soft2242.one.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.mybatis.service.impl.BaseServiceImpl;
import com.soft2242.one.convert.PhoneConvert;
import com.soft2242.one.dao.PhoneDao;
import com.soft2242.one.entity.PhoneEntity;
import com.soft2242.one.query.PhoneQuery;
import com.soft2242.one.service.PhoneService;
import com.soft2242.one.vo.PhoneVO;
import lombok.AllArgsConstructor;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电话表
 *
 * @author xuelong
 * @since 1.0.0 2023-06-07
 */
@Service
@AllArgsConstructor
public class PhoneServiceImpl extends BaseServiceImpl<PhoneDao, PhoneEntity> implements PhoneService {

    @Override
    public PageResult<PhoneVO> page(PhoneQuery query) {
        IPage<PhoneEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(PhoneConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<PhoneEntity> getWrapper(PhoneQuery query) {
        LambdaQueryWrapper<PhoneEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotEmpty(query.getCommunityId()), PhoneEntity::getCommunityId, query.getCommunityId());
        wrapper.eq(StringUtils.isNotEmpty(query.getType()), PhoneEntity::getType, query.getType());
        wrapper.like(StringUtils.isNotEmpty(query.getName()), PhoneEntity::getName, query.getName());
        wrapper.eq(StringUtils.isNotEmpty(query.getPhone()), PhoneEntity::getPhone, query.getPhone());
        return wrapper;
    }

    @Override
    public void save(PhoneVO vo) {
        PhoneEntity entity = PhoneConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(PhoneVO vo) {
        PhoneEntity entity = PhoneConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}
