package com.ybd.noname.service;

import com.ybd.noname.entities.RoleEntities;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 16:28
 */
public interface RoleService {
    /**
     * 获取会员权限列表
     * @return
     */
    List<RoleEntities> getAllRole();
}
