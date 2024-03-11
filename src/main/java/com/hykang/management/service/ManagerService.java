package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Manager;
import com.hykang.management.entity.vo.ManagerVo;
import com.hykang.management.entity.vo.ManagerLoginVo;

import java.util.List;

public interface ManagerService extends IService<Manager> {
     List<ManagerVo> findManagerByPage(Integer startPage, Integer pageSize, String username);
     long findManagerCount();
     ManagerLoginVo login(ManagerLoginVo managerLoginVo);
     boolean savaManager(Manager manager);
     boolean deleteManagerById(long id);
     int batchDeleteManager(List<Long> ids);
     boolean updateManagerById(Manager manager);
     boolean updateManagerRoleId(Manager manager);
     ManagerVo findManagerById(long id);
     Manager findManagerByName(String username);
     Manager findManagerByIdAndName(long id,String username);
     Boolean updateManagerStatus(Manager manager);
}
