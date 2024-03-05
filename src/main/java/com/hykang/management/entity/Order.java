package com.hykang.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    // 指明主键
    @TableId("order_id")
    private Integer orderId;
    private Integer userId;
    private String orderNumber;
    private BigDecimal orderPrice;
    private Integer orderPay;
    private String isSend;
    private String tradeNo;
    private String orderInvoiceContent;
    private String consigneeName;
    private String consigneePhone;
    private String consigneeAddress;
    private Integer payStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
