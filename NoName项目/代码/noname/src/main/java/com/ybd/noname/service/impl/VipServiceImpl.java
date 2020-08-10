package com.ybd.noname.service.impl;

import com.ybd.noname.dao.VipDAO;
import com.ybd.noname.entities.VipEntities;
import com.ybd.noname.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/5 0:12
 */
@Service
public class VipServiceImpl  implements VipService {
    @Autowired
    private VipDAO vipDAO;

    @Override
    @CachePut(cacheNames = "vip", key = "#result.username")
    public VipEntities editVipByDynamic(VipEntities vipEntities) {
        vipDAO.editVipByDynamic(vipEntities);
        return vipDAO.getOneById(vipEntities.getId());
    }

    @Override
    public List<VipEntities> getAllVip() {
        return vipDAO.getAllVip();
    }

    @Override
    public VipEntities getOneById(Integer id) {
        return vipDAO.getOneById(id);
    }

    /**
     * 完成缓存
     * @param id
     * @param status
     * @return 作为缓存的 key
     */
    @Override
    @CachePut(cacheNames = "vip", key = "#result.username")
    public VipEntities editStatusById(Integer id, Integer status) {
        vipDAO.editStatusById(id, status);
        return vipDAO.getOneById(id);
    }

    /**
     * 缓存成功
     * @param username 作为缓存的 key
     * @return
     */
    @Override
    @Cacheable(cacheNames = "vip", key = "#username")
    public VipEntities getOneByName(String username) {
       return vipDAO.getOneByName(username);
    }

    /**
     * 添加用户、
     * @param vipEntities
     * @return
     *
     * 关于缓存
     *  @CachePut(cacheNames = "vip", key = "#vipEntities.username")
     *  问题1：进入的数据有问题  以返回值作为 value，
     *  解决：添加返回值、
     *  业务逻辑问题2：添加完这个数据、如果这个数据有订单是查询不到的
     *  解决：在修改或者添加订单的时候 使用 cachePut
     */
    @Override
    @CachePut(cacheNames = "vip", key = "#vipEntities.username")
    public VipEntities addByDynamic(VipEntities vipEntities) {
        vipDAO.addByDynamic(vipEntities);
        return vipEntities;
    }

    @Override
    public List<VipEntities> getAllVipByLimit(Integer startPage, int pageSize) {
        return vipDAO.getAllVipByLimit(startPage,pageSize);
    }
}
