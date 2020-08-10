package com.ybd.noname.dao;

import com.ybd.noname.entities.OrderEntities;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/4 16:44
 */
@Mapper
public interface OrderDAO {
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
     * 根据 oderNumber 删除一个订单
     * @param oderNumber
     */
    void deleteOneByOderNumber(String oderNumber);

    /**
     * 添加订单
     * @param orderEntities
     */
    void addOrder(OrderEntities orderEntities);

    /**
     * 通过订单号查询到一个订单
     * @param oderNumber
     * @return
     */
    OrderEntities getOneByoderNumber(String oderNumber);

    /**
     * 分页查询
     * @param startPage
     * @param pageSize
     * @return
     */
    List<OrderEntities> getAllVipByLimit(Integer startPage, int pageSize);
}
