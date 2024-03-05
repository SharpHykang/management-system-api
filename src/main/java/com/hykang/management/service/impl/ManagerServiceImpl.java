package com.hykang.management.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hykang.management.entity.Manager;
import com.hykang.management.entity.dto.ManagerCustom;
import com.hykang.management.mapper.ManagerMapper;
import com.hykang.management.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service的意思是将其实例化,
//之前如果有一个class，是不是需要new一个对象才可以用，
//这里就可以理解成会自动帮你new，new完了之后，将实例加到了spring容器当中
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    //引进ManagerMapper,@Autowired的意思就是将其实例化，通过autowired从spring容器中拿出来，供我们使用
    @Autowired
    private ManagerMapper managerMapper;

    //处理业务逻辑，这里由于业务过于简单，就空着，查询出什么我就返回什么
    @Override
    public List<Manager> findManagerByPage(Integer startPage, Integer pageSize, String username){
        List<Manager> managerByPage = managerMapper.findManagerByPage(startPage, pageSize, username);
        if(managerByPage.size()==0){
            return null;
        }
        return managerByPage;
    }

    @Override
    public long findManagerCount(){
        return managerMapper.findManagerCount();
    }

    @Override
    public ManagerCustom login(ManagerCustom managerCustom) {
        return managerMapper.login(managerCustom);
    }

    @Override
    public boolean savaManager(Manager manager) {
        return managerMapper.savaManager(manager);
    }

    @Override
    public boolean deleteManagerById(long id) {
        return managerMapper.deleteManagerById(id);
    }

    @Override
    public int batchDeleteManager(List<Long> ids) {
        return managerMapper.batchDeleteManager(ids);
    }

    @Override
    public boolean updateManagerById(Manager manager) {
        return managerMapper.updateManagerById(manager);
    }

    @Override
    public Manager findManagerById(long id) {
        return managerMapper.findManagerById(id);
    }

    @Override
    public Manager findManagerByName(String username) {
        return managerMapper.findManagerByName(username);
    }

    @Override
    public Manager findManagerByIdAndName(long id, String username) {
        return managerMapper.findManagerByIdAndName(id,username);
    }

}
