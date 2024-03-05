package com.hykang.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hykang.management.entity.PermissionApi;
import com.hykang.management.entity.dto.PermissionCustom;
import com.hykang.management.entity.vo.PermissionParentChildVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionApiMapper extends BaseMapper<PermissionApi> {
    // 获取权限列表
    List<PermissionCustom> getAllRightList();
    // 获取权限树状列表
    List<PermissionParentChildVo> getAllRightTree();
    // 获取权限菜单
    List<PermissionParentChildVo> getMenus();
}
