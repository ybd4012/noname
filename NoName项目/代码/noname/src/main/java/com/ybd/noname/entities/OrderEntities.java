package com.ybd.noname.entities;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author yeah
 * @date 2020/8/4 16:31
 */
@Data
@ToString
public class OrderEntities implements Serializable {
    /**
     *  一个订单一个用户
     */
    private VipEntities vipEntities;

    /**
     * 唯一主键
     */
    private int id;
    /**
     * 唯一订单号
     */
    private String oderNumber;
    /**
     * 收货人和及其手机号码
     */
    private String consignee;
    /**
     * 总金额
     */
    private BigDecimal amount;
    /**
     * 应付金额
     */
    private BigDecimal amountPayable;
    /**
     * 支付状态
     */
    private String orderStatus;
    /**
     * 发货状态
     */
    private String deliveryStatus;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 顺丰等发货方式
     */
    private String distributionMode;
    /**
     * 订单创建时间
     */
    private Timestamp orderCreatTime;
    /**
     * 谁下的订单
     */
    private Integer vipId;
}
