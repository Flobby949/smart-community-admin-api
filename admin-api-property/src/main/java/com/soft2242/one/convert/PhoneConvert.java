package com.soft2242.one.convert;


import com.soft2242.one.entity.PhoneEntity;
import com.soft2242.one.vo.PhoneVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 电话表
*
* @author xuelong
* @since 1.0.0 2023-06-07
*/
@Mapper
public interface PhoneConvert {
    PhoneConvert INSTANCE = Mappers.getMapper(PhoneConvert.class);

    PhoneEntity convert(PhoneVO vo);

    PhoneVO convert(PhoneEntity entity);

    List<PhoneVO> convertList(List<PhoneEntity> list);

}
