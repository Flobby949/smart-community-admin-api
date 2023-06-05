package com.soft2242.one.controller;

import com.soft2242.one.base.common.utils.PageResult;
import com.soft2242.one.base.common.utils.Result;
import com.soft2242.one.convert.DoorConvert;
import com.soft2242.one.entity.DoorEntity;
import com.soft2242.one.query.DoorQuery;
import com.soft2242.one.service.DoorService;
import com.soft2242.one.storage.service.StorageService;
import com.soft2242.one.vo.DoorVO;
import com.soft2242.one.vo.SysFileUploadVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* 门禁管理
*
* @author Flobby 
* @since 1.0.0 2023-05-29
*/
@RestController
@RequestMapping("smart/door")
@Tag(name="门禁管理")
@AllArgsConstructor
public class DoorController {
    private final DoorService doorService;
    private final StorageService storageService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:door:page')")
    public Result<PageResult<DoorVO>> page(@ParameterObject @Valid DoorQuery query){
        PageResult<DoorVO> page = doorService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:door:info')")
    public Result<DoorVO> get(@PathVariable("id") Long id){
        DoorEntity entity = doorService.getById(id);

        return Result.ok(DoorConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:door:save')")
    public Result<String> save(@RequestBody DoorVO vo){
        doorService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:door:update')")
    public Result<String> update(@RequestBody @Valid DoorVO vo){
        doorService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:door:delete')")
    public Result<String> delete(@RequestBody List<Long> idList){
        doorService.delete(idList);

        return Result.ok();
    }

    @GetMapping("list")
    @Operation(summary = "删除")
    public Result<List<DoorEntity>> all(){

        return Result.ok(doorService.list());
    }

    @PostMapping("upload")
    @Operation(summary = "上传门禁图片")
    public Result<SysFileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的文件");
        }
        // 上传路径
        String path = storageService.getPath(file.getOriginalFilename());
        // 上传文件
        String url = storageService.upload(file.getBytes(), path);
        SysFileUploadVO vo = new SysFileUploadVO();
        vo.setUrl(url);
        vo.setSize(file.getSize());
        vo.setName(file.getOriginalFilename());
        vo.setPlatform(storageService.properties.getConfig().getType().name());
        return Result.ok(vo);
    }
}