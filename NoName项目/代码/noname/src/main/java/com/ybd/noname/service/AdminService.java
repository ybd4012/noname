package com.ybd.noname.service;

import com.ybd.noname.entities.AdminEntities;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 17:28
 */
public interface AdminService {
    /**
     * 获取所有管理员
     * @return 返回所有管理员
     */
    List<AdminEntities> getAllAdmin();

    void editAdmin(AdminEntities adminEntities);

    void deleteById(Integer id);

    void editStatusById(Integer id , Integer status);

    List<AdminEntities> getAdminByDynamic(AdminEntities adminEntities);

    void addAdminByDynamic(AdminEntities adminEntities);

    AdminEntities getAdminByName(AdminEntities adminEntities);

    List<AdminEntities> getAllVipByLimit(Integer startPage, Integer pageSize);
}
