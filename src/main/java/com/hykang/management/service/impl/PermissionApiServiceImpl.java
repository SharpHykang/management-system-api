package com.hykang.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hykang.management.entity.PermissionApi;
import com.hykang.management.entity.dto.PermissionCustom;
import com.hykang.management.entity.vo.PermissionParentChildVo;
import com.hykang.management.mapper.PermissionApiMapper;
import com.hykang.management.service.PermissionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionApiServiceImpl extends ServiceImpl<PermissionApiMapper, PermissionApi> implements PermissionApiService {


    @Autowired
    private PermissionApiMapper permissionApiMapper;

    @Override
    public List<PermissionCustom> getAllRightList() {
        List<PermissionCustom> allRightList = permissionApiMapper.getAllRightList();
        if(allRightList.size()==0){
            return null;
        }
        return allRightList;
    }

    @Override
    public List<PermissionParentChildVo> getAllRightTree(){
        List<PermissionParentChildVo> allRightTree = permissionApiMapper.getAllRightTree();
        if(allRightTree.size()==0){
            return null;
        }
        return allRightTree;
    }

    @Override
    public List<PermissionParentChildVo> getMenus() {
        List<PermissionParentChildVo> menus = permissionApiMapper.getMenus();
        if(menus.size()==0){
            return null;
        }
        return menus;
    }
}