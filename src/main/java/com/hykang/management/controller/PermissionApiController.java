package com.hykang.management.controller;

import com.hykang.management.common.Result;
import com.hykang.management.entity.PermissionApi;
import com.hykang.management.entity.dto.PermissionCustom;
import com.hykang.management.entity.vo.PermissionParentChildVo;
import com.hykang.management.service.PermissionApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/rights",produces = "application/json;charset=utf-8")
@Tag(name = "权限接口",description = "权限相关接口")
public class PermissionApiController {

    @Autowired
    private PermissionApiService permissionApiService;

    /**
     * 查询所有权限：list列表结果
     * @return
     */
    @Operation(summary = "查询所有权限list")
    @GetMapping("/list")
    public Result<List<PermissionCustom>> getAllRightList(){
        List<PermissionCustom> permissionCustomList=permissionApiService.getAllRightList();
        if(permissionCustomList!=null){
            return Result.success(permissionCustomList);
        }else{
            return Result.error("未查到数据！");
        }
    }

    /**
     * 查询所有权限：tree树状结果
     * @return
     */
    @Operation(summary = "查询所有权限tree")
    @GetMapping("/tree")
    public Result<List<PermissionParentChildVo>> getAllRightTree(){
        List<PermissionParentChildVo> permissionParentChildVoList=permissionApiService.getAllRightTree();
        if(permissionParentChildVoList!=null){
            return Result.success(permissionParentChildVoList);
        }else{
            return Result.error("未查到数据！");
        }
    }
}
