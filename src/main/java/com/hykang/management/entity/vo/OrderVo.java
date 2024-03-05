package com.hykang.management.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hykang.management.entity.Order;
import com.hykang.management.entity.OrderGoods;
import lombok.Data;

import java.util.List;

@Data
public class OrderVo extends Order {
    @TableField(exist=false)
    private List<OrderGoods> goods;
}
