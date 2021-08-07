package com.suncj.geektask.week07.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeOrderDetailEntity {

    private long id;

    private String orderId;

    private long productId;

    private String productName;

    private int buyNum;

    private BigDecimal productPrice;

    private BigDecimal averageCost;

    private BigDecimal feeMoney;

    private long warehouseId;

    private Date createTime;

    private Date updateTime;
}
