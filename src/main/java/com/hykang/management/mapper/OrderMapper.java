package com.hykang.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hykang.management.entity.Order;
import com.hykang.management.entity.Role;
import com.hykang.management.entity.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    // 分页查询
    List<Order> getOrderByPage(@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize,@Param("orderNumber") String orderNumber,@Param("isSend") Integer isSend,@Param("payStatus") Integer payStatus);

    // 总数查询
    long getOrderCount();

    // 通过id查询订单
    OrderVo getOrderById(Integer id);

    // 修改订单
    boolean updateOrder(Order order);

    // 删除订单：删除订单数据，和对应订单商品数据
    // 删除订单数据
    boolean deleteOrderById(Integer id);

    // 删除对应的商品中间表数据
    boolean deleteOrderGoodsByOrderId(Integer orderId);
}
