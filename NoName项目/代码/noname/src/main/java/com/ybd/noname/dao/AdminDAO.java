package com.ybd.noname.dao;

import com.ybd.noname.entities.AdminEntities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 17:14
 */
@Mapper
public interface AdminDAO {
    /**
     * 获取所有管理员
     * @return 返回所有管理员
     */
    List<AdminEntities> getAllAdmin();

    /**
     * 编辑一个已存在的管理员
     * @param adminEntities
     */
    void editAdmin(AdminEntities adminEntities);

    /**
     * 根据 id 真正意义上删除一个用户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改状态
     * @param id
     * @param status
     */
    void editStatusById(@Param("id") Integer id, @Param("status")Integer status);


    /**
     * 根据 登录名、手机号码、或者邮箱查询出一个或者多个admin
     * @param adminEntities
     * @return
     */
    List<AdminEntities> getAdminByDynamic(AdminEntities adminEntities);

    /**
     * 根据 登录名（必须）、手机号码、或者邮箱保存一个用户  默认密码123456
     * @param adminEntities
     */
    void addAdminByDynamic(AdminEntities adminEntities);

    /**
     * 通过name获取一个用户
     * @param adminEntities
     * @return
     */
    AdminEntities getAdminByName(AdminEntities adminEntities);

    /**
     * 分页查询
     * @param startPage
     * @param pageSize
     * @return
     */
    List<AdminEntities> getAllVipByLimit(Integer startPage, Integer pageSize);
}
