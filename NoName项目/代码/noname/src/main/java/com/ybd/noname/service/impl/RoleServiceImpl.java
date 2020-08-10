package com.ybd.noname.service.impl;

import com.ybd.noname.dao.RoleDAO;
import com.ybd.noname.entities.RoleEntities;
import com.ybd.noname.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 16:29
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;
    /**
     * 获取所有角色对应的权限
     * @return
     */
    @Override
    public List<RoleEntities> getAllRole() {
        return roleDAO.getAllRole();
    }
}
