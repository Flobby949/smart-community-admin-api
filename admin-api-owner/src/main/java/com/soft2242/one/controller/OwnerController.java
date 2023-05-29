package com.soft2242.one.controller;

import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.common.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import com.soft2242.one.convert.OwnerConvert;
import com.soft2242.one.entity.OwnerEntity;
import com.soft2242.one.service.OwnerService;
import com.soft2242.one.query.OwnerQuery;
import com.soft2242.one.vo.OwnerVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
* 业主表
*
* @author lsc lsc666@qq.com
* @since 1.0.0 2023-05-28
*/
@RestController
@RequestMapping("one/owner")
@Tag(name="业主表")
@AllArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("page")
    @Operation(summary = "分页")
//    @PreAuthorize("hasAuthority('one:owner:page')")
    public Result<PageResult<OwnerVO>> page(@ParameterObject @Valid OwnerQuery query){
        PageResult<OwnerVO> page = ownerService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
//    @PreAuthorize("hasAuthority('one:owner:info')")
    public Result<OwnerVO> get(@PathVariable("id") Long id){
        OwnerEntity entity = ownerService.getById(id);
        return Result.ok(OwnerConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
//    @PreAuthorize("hasAuthority('one:owner:save')")
    public Result<String> save(@RequestBody OwnerVO vo){
        ownerService.save(vo);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
//    @PreAuthorize("hasAuthority('one:owner:update')")
    public Result<String> update(@RequestBody @Valid OwnerVO vo){
        ownerService.update(vo);
        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
//    @PreAuthorize("hasAuthority('one:owner:delete')")
    public Result<String> delete(@RequestBody List<Long> idList){
        ownerService.delete(idList);
        return Result.ok();
    }
}