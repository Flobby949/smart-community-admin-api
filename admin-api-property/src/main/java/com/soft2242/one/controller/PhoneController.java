package com.soft2242.one.controller;

import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.common.utils.Result;
import com.soft2242.one.convert.PhoneConvert;
import com.soft2242.one.entity.PhoneEntity;
import com.soft2242.one.query.PhoneQuery;
import com.soft2242.one.service.PhoneService;
import com.soft2242.one.vo.PhoneVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
* 电话表
*
* @author xuelong
* @since 1.0.0 2023-06-07
*/
@RestController
@RequestMapping("/property/phone")
@Tag(name="电话表")
@AllArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @GetMapping("page")
    @Operation(summary = "分页")
//    @PreAuthorize("hasAuthority('one:phone:page')")
    public Result<PageResult<PhoneVO>> page(@ParameterObject @Valid PhoneQuery query){
        PageResult<PhoneVO> page = phoneService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
//    @PreAuthorize("hasAuthority('one:phone:info')")
    public Result<PhoneVO> get(@PathVariable("id") Long id){
        PhoneEntity entity = phoneService.getById(id);

        return Result.ok(PhoneConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
//    @PreAuthorize("hasAuthority('one:phone:save')")
    public Result<String> save(@RequestBody PhoneVO vo){
        phoneService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
//    @PreAuthorize("hasAuthority('one:phone:update')")
    public Result<String> update(@RequestBody @Valid PhoneVO vo){
        phoneService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
//    @PreAuthorize("hasAuthority('one:phone:delete')")
    public Result<String> delete(@RequestBody List<Long> idList){
        phoneService.delete(idList);

        return Result.ok();
    }
}
