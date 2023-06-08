package com.soft2242.one.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.soft2242.one.base.common.constant.Constant;
import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.common.utils.Result;

import com.soft2242.one.constant.OwnerConstant;
import com.soft2242.one.convert.OwnerConvert;
import com.soft2242.one.entity.OwnerEntity;
import com.soft2242.one.entity.UserEntity;
import com.soft2242.one.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import com.soft2242.one.service.OwnerService;
import com.soft2242.one.query.OwnerQuery;
import com.soft2242.one.vo.OwnerVO;
import org.springdoc.core.annotations.ParameterObject;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final UserService userService;

    @GetMapping("page")
    @Operation(summary = "分页")
//    @PreAuthorize("hasAuthority('one:owner:page')")
    public Result<PageResult<OwnerVO>> page(@ParameterObject @Valid OwnerQuery query){
        PageResult<OwnerVO> page = ownerService.page(query);
        return Result.ok(page);
    }
    @PostMapping("list")
    @Operation(summary = "业主信息获取")
    public Result<List<OwnerEntity>> list(){
        QueryWrapper<OwnerEntity> wrapper = new QueryWrapper<>();
        wrapper.select(OwnerConstant.DEDUPLICATION_SQL);
        List<OwnerEntity> list = ownerService.list(wrapper).stream().filter(owner -> !Objects.equals(owner.getIdentityCard(), "")).collect(Collectors.toList());
        return Result.ok(list);
    }
    @PostMapping("findFamily")
    @Operation(summary = "获取家庭成员信息")
    public Result<List<OwnerVO>> findFamily(Long ownerId){
        QueryWrapper<OwnerEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().select(OwnerEntity::getRealName,OwnerEntity::getPhone,OwnerEntity::getIdentityCard,OwnerEntity::getIdentity,OwnerEntity::getGender,OwnerEntity::getId)
                .eq(OwnerEntity::getOwnerId,ownerId).eq(OwnerEntity::getState,1).ne(OwnerEntity::getDeleted,1);
        List<OwnerEntity> list = ownerService.list(wrapper);
        List<OwnerVO> ownerVOS = OwnerConvert.INSTANCE.convertList(list);
        for(OwnerVO owner:ownerVOS){
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(UserEntity::getPhone,owner.getPhone());
            if(userService.count(queryWrapper)==0) {
                owner.setIsRegister(OwnerConstant.UNREGISTERED);
            }else{
                owner.setIsRegister(OwnerConstant.REGISTERED);
            }
        }
        return Result.ok(ownerVOS);
    }
    @PostMapping("deFamily")
    @Operation(summary = "删除家庭成员")
    public Result<String> deFamily(Long id){
        UpdateWrapper<OwnerEntity> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(OwnerEntity::getDeleted,1).eq(OwnerEntity::getId,id);
        ownerService.update(new OwnerEntity(),wrapper);
        return Result.ok();
    }
    @GetMapping("{id}")
    @Operation(summary = "信息")
//    @PreAuthorize("hasAuthority('one:owner:info')")
    public Result<OwnerVO> get(@PathVariable("id") Long id){

        OwnerVO list = ownerService.findOwnerInfo(id);
        return Result.ok(list);
    }
    @GetMapping("family/{id}")
    @Operation(summary = "家属/租户详细信息")
    public Result<OwnerVO> familyInfo(@PathVariable("id") Long id){
        QueryWrapper<OwnerEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().select(OwnerEntity::getGender,OwnerEntity::getRealName,OwnerEntity::getIdentityCard,OwnerEntity::getPhone,OwnerEntity::getIdentity,OwnerEntity::getCreateTime).eq(OwnerEntity::getId,id);
        OwnerEntity one = ownerService.getOne(wrapper);
        OwnerVO vo = OwnerConvert.INSTANCE.convert(one);
        return Result.ok(vo);
    }
    @PostMapping("approvedApply")
    @Operation(summary = "审核通过业主申请")
    public Result<String> approvedApply(@RequestBody Map<String, Long> body){
        Long id = body.get("id");
        ownerService.approvedApply(id);
        return Result.ok();
    }
    @PostMapping("refuseApply")
    @Operation(summary = "审核拒绝业主申请")
    public Result<String> refuseApply(@RequestBody Map<String, Long> body){
        Long id = body.get("id");
        ownerService.refuseApply(id);
        return Result.ok();
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