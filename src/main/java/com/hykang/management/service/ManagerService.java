package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Manager;
import com.hykang.management.entity.dto.ManagerCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerService extends IService<Manager> {
     List<Manager> findManagerByPage(Integer startPage, Integer pageSize, String username);
     long findManagerCount();
     ManagerCustom login(ManagerCustom managerCustom);
     boolean savaManager(Manager manager);
     boolean deleteManagerById(long id);
     int batchDeleteManager(List<Long> ids);
     boolean updateManagerById(Manager manager);
     Manager findManagerById(long id);
     Manager findManagerByName(String username);
     Manager findManagerByIdAndName(long id,String username);
}
