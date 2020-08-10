package com.ybd.noname.service;

import com.ybd.noname.entities.OrderEntities;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/6 15:06
 */
public interface OrderService {
    /** 分布查询
     * 查询订单 根据 vipId
     * @param vipId
     * @return
     */
    List<OrderEntities> getOneByIdJoint(Integer vipId);

    /** 非分步查询
     * 查询订单 根据 vipId
     * @param vId
     * @return
     */
    List<OrderEntities> getOneByVid(Integer vId);

    /**
     * 查询出所有订单然后 分布查询出每个订单的相关信息
     * @return
     */
    List<OrderEntities> getAllOrder();

    /**
     * 根据 ID 删除一个订单
     * @param oderNumber
     */
    void deleteOneByOderNumber(String oderNumber);

    OrderEntities addOrder(OrderEntities orderEntities);

    OrderEntities getOneByoderNumber(String oderNumber);

    List<OrderEntities> getAllVipByLimit(Integer startPage, int pageSize);
}
