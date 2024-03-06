package com.hykang.management.controller;


import com.hykang.management.common.Pager;
import com.hykang.management.common.Result;
import com.hykang.management.entity.Manager;
import com.hykang.management.entity.dto.ManagerCustom;
import com.hykang.management.service.ManagerService;
import com.hykang.management.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/managers",produces = "application/json;charset=utf-8")
@Tag(name = "管理员接口",description = "管理员相关接口")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**用户登录
     * @param managerCustom
     * @return
     */
    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Result<Manager> login(@RequestBody ManagerCustom managerCustom){
        String password=managerCustom.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        ManagerCustom loginManager = managerService.login(managerCustom);
        if(loginManager==null){
            return Result.error("用户名不存在，登录失败！");
        }
        if(!loginManager.getPassword().equals(password)){
            return Result.error("密码错误，登录失败！");
        }
        if(loginManager.getStatus().equals(false)){
            return Result.error("账号已禁用！");
        }
        // 生成token
        String token = TokenUtils.sign(managerCustom);
        loginManager.setToken(token);
        //设置password为null，防止密码泄露
        loginManager.setPassword(null);
        return Result.success(loginManager);
    }

    /**
     * 用户分页查询
     * @param pageNum
     * @param pageSize
     * @param username
     * @return
     */
    @Operation(summary = "管理员分页查询")
    @Parameters({
            @Parameter(name = "pageNum",description = "页码"),
            @Parameter(name = "pageSize",description = "每页条数"),
            @Parameter(name = "username",description = "用户名")
    })
    @GetMapping("/getManagerByPage")
    public Result<Pager<Manager>> getManagerByPage(Integer pageNum, Integer pageSize, String username){
        log.info("pageNum="+pageNum+"，pageSize="+pageSize+"，username="+username);
        List<Manager> Managers = managerService.findManagerByPage((pageNum-1)*pageSize,pageSize,username);
        if(Managers!=null){
            Pager<Manager> pager=new Pager();
            pager.setPageNum(pageNum);
            pager.setPageSize(pageSize);
            pager.setData(Managers);
            pager.setTotal(managerService.findManagerCount());
            return Result.success(pager);
        }else {
            return Result.error("未查到数据！");
        }
    }

    /**
     * 新增用户
     * @param manager
     * @return
     */
    @Operation(summary = "新增管理员")
    @PostMapping("/savaManager")
    public Result<String> savaManager(@RequestBody Manager manager){
        log.info("manager="+manager);
        Manager managerByName = managerService.findManagerByName(manager.getUsername());
        if(managerByName!=null){
            return Result.error("用户名已存在！");
        }
        // 对密码进行md5加密存入数据库
        manager.setPassword(DigestUtils.md5DigestAsHex(manager.getPassword().getBytes()));
        boolean flag = managerService.savaManager(manager);
        if(flag){
            return Result.success("用户添加成功！");
        }else{
            return Result.error("用户添加失败！");
        }
    }

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    @Operation(summary = "删除管理员")
    @Parameter(name = "id",description = "管理员id")
    @DeleteMapping("/deleteManagerById/{id}")
    public Result<String> deleteManagerById(@PathVariable long id){
        boolean flag = managerService.deleteManagerById(id);
        if(flag){
            return Result.success("用户删除成功！");
        }else {
            return Result.error("用户删除失败！");
        }
    }

    /**
     * 修改用户数据
     * @param manager
     * @return
     */
    @Operation(summary = "修改管理员")
    @PutMapping("/updateManagerById")
    public Result<String> updateManagerById(@RequestBody Manager manager){
        Manager managerByIdAndName = managerService.findManagerByIdAndName(manager.getId(), manager.getUsername());
        if(managerByIdAndName!=null){
            return Result.error("用户名已存在！");
        }
        // 对密码进行md5加密存入数据库
        manager.setPassword(DigestUtils.md5DigestAsHex(manager.getPassword().getBytes()));
        boolean flag = managerService.updateManagerById(manager);
        if(flag){
            return Result.success("用户修改成功！");
        }else{
            return Result.error("用户修改失败！");
        }
    }

    /**
     * 用户角色分配
     * @param manager
     * @return
     */
    @Operation(summary = "用户角色分配")
    @PutMapping("/updateManagerRoleId")
    public Result<String> updateManagerRoleId(@RequestBody Manager manager){
        boolean flag = managerService.updateManagerRoleId(manager);
        if(flag){
            return Result.success("用户角色分配成功！");
        }else{
            return Result.error("用户角色分配失败！");
        }
    }

    /**
     * 通过Id查询用户
     * @param id
     * @return
     */
    @Operation(summary = "通过Id查询管理员")
    @Parameter(name = "id",description = "管理员id")
    @GetMapping("/findManagerById/{id}")
    public Result<Manager> findManagerById(@PathVariable long id){
        Manager Manager = managerService.findManagerById(id);
        if(Manager!=null){
            return Result.success(Manager);
        }else{
            return Result.error("未查到数据！");
        }
    }
}
