package com.ybd.noname.service;

import com.ybd.noname.entities.VipEntities;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/5 0:11
 */
public interface VipService {
    /**
     *
     * @param vipEntities
     */
    VipEntities editVipByDynamic(VipEntities vipEntities);

    /**
     * 查询所有  采用分步查询、懒加载  传入id获取到多个订单
     * @return
     */
    List<VipEntities> getAllVip();

    /**
     * 根据id获取一个vip  采用分步查询、懒加载
     * @param id
     * @return
     */
    VipEntities getOneById(Integer id);

    VipEntities editStatusById(Integer id, Integer status);

    VipEntities getOneByName(String username);

    VipEntities addByDynamic(VipEntities vipEntities);

    List<VipEntities> getAllVipByLimit(Integer startPage, int pageSize);
}
