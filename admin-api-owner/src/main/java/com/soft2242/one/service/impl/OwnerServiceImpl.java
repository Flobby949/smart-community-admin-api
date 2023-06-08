package com.soft2242.one.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.mybatis.service.impl.BaseServiceImpl;
import com.soft2242.one.convert.OwnerConvert;
import com.soft2242.one.dao.OwnerDao;
import com.soft2242.one.entity.House;
import com.soft2242.one.entity.OwnerEntity;
import com.soft2242.one.query.OwnerQuery;
import com.soft2242.one.service.IHouseService;
import com.soft2242.one.service.OwnerService;
import com.soft2242.one.vo.OwnerVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业主表
 *
 * @author lsc lsc666@qq.com
 * @since 1.0.0 2023-05-28
 */
@Service
@AllArgsConstructor
public class OwnerServiceImpl extends BaseServiceImpl<OwnerDao, OwnerEntity> implements OwnerService {
    private final IHouseService houseService;

    @Override
    public PageResult<OwnerVO> page(OwnerQuery query) {
        query.setStart((query.getPage()-1) * query.getLimit());
        return new PageResult<>(baseMapper.findOwnerOQ(query), baseMapper.findOwnerRecordByOQ(query));
    }

    @Override
    public OwnerVO findOwnerInfo(Long id) {
        return baseMapper.findOwnerInfo(id);
    }

    @Override
    public void approvedApply(Long id) {
        LambdaUpdateWrapper<OwnerEntity> wrapper= Wrappers.lambdaUpdate();
        wrapper.set(OwnerEntity::getState,1).eq(OwnerEntity::getId,id);
        baseMapper.update(new OwnerEntity(),wrapper);
    }

    @Override
    public void refuseApply(Long id) {
        LambdaUpdateWrapper<OwnerEntity> wrapper= Wrappers.lambdaUpdate();
        wrapper.set(OwnerEntity::getState,2).eq(OwnerEntity::getId,id);
        baseMapper.update(new OwnerEntity(),wrapper);
        House house = houseService.getById(baseMapper.selectById(id).getHouseId());
        house.setHouseStatus((byte) 0);
        houseService.updateById(house);
    }

    private LambdaQueryWrapper<OwnerEntity> getWrapper(OwnerQuery query){
        LambdaQueryWrapper<OwnerEntity> wrapper = Wrappers.lambdaQuery();
        return wrapper;
    }

    @Override
    public void save(OwnerVO vo) {
        OwnerEntity entity = OwnerConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(OwnerVO vo) {
        OwnerEntity entity = OwnerConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}