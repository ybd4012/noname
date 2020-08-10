package com.ybd.noname.service.impl;

import com.ybd.noname.dao.AdminDAO;
import com.ybd.noname.entities.AdminEntities;
import com.ybd.noname.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 17:29
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Override
    public List<AdminEntities> getAllAdmin() {
        return adminDAO.getAllAdmin();
    }

    @Override
    public void editAdmin(AdminEntities adminEntities) {
        adminDAO.editAdmin(adminEntities);
    }

    @Override
    public void deleteById(Integer id) {
        adminDAO.deleteById(id);
    }

    @Override
    public void editStatusById(Integer id ,Integer status) {
        adminDAO.editStatusById(id,status);
    }

    @Override
    public List<AdminEntities> getAdminByDynamic(AdminEntities adminEntities) {
       return adminDAO.getAdminByDynamic(adminEntities);
    }

    @Override
    public void addAdminByDynamic(AdminEntities adminEntities) {
        adminDAO.addAdminByDynamic(adminEntities);
    }

    @Override
    public AdminEntities getAdminByName(AdminEntities adminEntities) {
        return adminDAO.getAdminByName(adminEntities);
    }

    @Override
    public List<AdminEntities> getAllVipByLimit(Integer startPage, Integer pageSize) {
        return adminDAO.getAllVipByLimit(startPage, pageSize);
    }
}
