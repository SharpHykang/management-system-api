package com.hykang.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hykang.management.entity.Role;
import com.hykang.management.entity.vo.PermissionApiParantChildVo;
import com.hykang.management.entity.vo.RoleParentChildVo;
import com.hykang.management.mapper.RoleMapper;
import com.hykang.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleParentChildVo> getAllRoleTree() {
        List<Role> roleList=getAllRoleList();
        if(roleList==null){
            return null;
        }
        List<RoleParentChildVo> roleParentChildVoList=new ArrayList<>();
        roleList.forEach(item->{
            RoleParentChildVo roleParentChildVo=new RoleParentChildVo();
            roleParentChildVo.setRoleId(item.getId());
            roleParentChildVo.setRoleName(item.getName());
            roleParentChildVo.setPermissionIds(item.getPermissionIds());
            roleParentChildVo.setRoleDesc(item.getDescription());
            roleParentChildVo.setChildren(roleMapper.getAllRoleTree(item.getId()));
            roleParentChildVoList.add(roleParentChildVo);
        });
        return roleParentChildVoList;
    }

    @Override
    public List<Role> getAllRoleList() {
        List<Role> allRoleList = roleMapper.getAllRoleList();
        if(allRoleList.size()==0){
            return null;
        }
        return allRoleList;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public boolean createRole(Role role) {
        return roleMapper.createRole(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public boolean setRole(Role role) {
        return roleMapper.setRole(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleMapper.getRoleByName(name);
    }

    @Override
    public Role getRoleByIdAndName(Integer id, String name) {
        return roleMapper.getRoleByIdAndName(id,name);
    }
}
