package com.ybd.noname.dao;

import com.ybd.noname.entities.VipEntities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/4 15:27
 */
@Mapper
public interface VipDAO {
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

    /**
     * 通过 id 修改状态 0——>1 或者 1——>0
     * @param id
     * @param status
     */
    void editStatusById(Integer id, Integer status);

    /** 动态sql
     *  根据 id 动态的修改传入的数据 例如 密码、手机号码、邮箱、性别、姓名
     * @param vipEntities
     */
    void editVipByDynamic(VipEntities vipEntities);

    /**
     * 根据用户名查询一个用户是否存在
     * @param username
     * @return VipEntities
     */
    VipEntities getOneByName(String username);

    /**
     * 动态的添加数据
     * @param vipEntities
     */
    void addByDynamic(VipEntities vipEntities);

    /**
     * vip 分页查询
     * @param startPage limit p1
     * @param pageSize  limit p2
     * @return
     */
    List<VipEntities> getAllVipByLimit(@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
}
