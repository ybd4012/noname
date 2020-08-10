package com.ybd.noname.service.impl;

import com.ybd.noname.dao.OrderDAO;
import com.ybd.noname.entities.OrderEntities;
import com.ybd.noname.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/6 15:07
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Override
    public List<OrderEntities> getOneByIdJoint(Integer vipId) {
        return orderDAO.getOneByIdJoint(vipId);
    }

    @Override
    public List<OrderEntities> getOneByVid(Integer vId) {
        return orderDAO.getOneByVid(vId);
    }


    @Override
    public List<OrderEntities> getAllOrder() {
        return orderDAO.getAllOrder();
    }

    @Override
    @CacheEvict(cacheNames = "order",key = "#oderNumber")
    public void deleteOneByOderNumber(String oderNumber) {
        orderDAO.deleteOneByOderNumber(oderNumber);
    }

    @Override
    @Cacheable(cacheNames = "order",key = "#orderEntities.oderNumber")
    public OrderEntities addOrder(OrderEntities orderEntities) {
        orderDAO.addOrder(orderEntities);
        return orderDAO.getOneByoderNumber(orderEntities.getOderNumber());
    }


    @Override
    @Cacheable(cacheNames = "order", key = "#oderNumber")
    public OrderEntities getOneByoderNumber(String oderNumber) {
        return orderDAO.getOneByoderNumber(oderNumber);
    }

    @Override
    public List<OrderEntities> getAllVipByLimit(Integer startPage, int pageSize) {
        return orderDAO.getAllVipByLimit(startPage,pageSize);
    }
}
