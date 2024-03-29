package com.hykang.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hykang.management.entity.Order;
import com.hykang.management.entity.vo.OrderVo;
import com.hykang.management.mapper.OrderMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService extends IService<Order> {
    List<OrderVo> getOrderByPage(Integer startPage,Integer pageSize,String orderNumber,Integer isSend,Integer payStatus);

    long getOrderCount(String orderNumber, Integer isSend, Integer payStatus);
    boolean updateOrder(OrderVo orderVo);

    OrderVo getOrderById(Integer id);

    boolean deleteOrderById(Integer id);
    boolean deleteOrderGoodsByOrderId(Integer orderId);
}
