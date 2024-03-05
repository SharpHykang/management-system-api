package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Role;
import com.hykang.management.entity.vo.PermissionApiParantChildVo;
import com.hykang.management.entity.vo.RoleParentChildVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<RoleParentChildVo> getAllRoleTree();

    List<Role> getAllRoleList();

    Role getRoleById(Integer id);

    boolean createRole(Role role);

    boolean updateRole(Role role);

    boolean deleteRole(Integer id);

    boolean setRole(Role role);

    Role getRoleByName(String name);

    Role getRoleByIdAndName(Integer id,String name);
}
