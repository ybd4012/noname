package com.ybd.noname.dao;

import com.ybd.noname.entities.RoleEntities;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 16:23
 */
@Mapper
public interface RoleDAO {
    /**
     * 获取会员权限列表
     * @return
     */
    List<RoleEntities> getAllRole();
}
