package com.suncj.geektask.week07.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeOrderEntity {
    /**
     * 订单ID
     */
    private Long id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 下单人ID
     */
    private Long customerId;
    /**
     * 收货人姓名
     */
    private String shippingUser;
    /**
     * 省
     */
    private int province;
    /**
     * 市
     */
    private int city;
    /**
     * 区
     */
    private int district;
    /**
     * 地址
     */
    private String address;
    /**
     * 支付方式：1现金，2余额，3网银，4支付宝，5微信
     */
    private int paymentMethod;
    /**
     * 订单金额
     */
    private BigDecimal orderMoney;
    /**
     * 优惠金额
     */
    private BigDecimal districtMoney;
    /**
     * 运费金额
     */
    private BigDecimal shippingMoney;

    /**
     * 快递公司名称
     */
    private String shippingCompName;
    /**
     * 快递单号
     */
    private String shippingSn;

    /**
     *发货时间
     */
    private Date sendTime;

    /**
     *支付时间
     */
    private Date payTime;

    /**
     * 收货时间
     */
    private Date receiveTime;

    /**
     * 订单状态
     */
    private int orderStatus;

    private long createTime;

    private long updateTime;
}
