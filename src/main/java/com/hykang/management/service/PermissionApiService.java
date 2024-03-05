package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.common.Result;
import com.hykang.management.entity.PermissionApi;
import com.hykang.management.entity.dto.PermissionCustom;
import com.hykang.management.entity.vo.PermissionParentChildVo;

import java.util.List;

public interface PermissionApiService extends IService<PermissionApi> {
    List<PermissionCustom> getAllRightList();

    List<PermissionParentChildVo> getAllRightTree();

    List<PermissionParentChildVo> getMenus();
}
