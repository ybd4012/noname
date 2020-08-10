package com.ybd.noname.dao;

import com.ybd.noname.entities.LogEntities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 13:00
 */
@Mapper
public interface LogDAO {
    /**
     * 通过 id 获取到一个日志
     * @param id
     * @return
     */
    LogEntities getLogById(@Param("id") Integer id);

    /**
     * 获取所有日志
     * @return
     */
    List<LogEntities> getLogAll();

    /**
     * 添加日志
     * @param logEntities
     */
    void addLog(LogEntities logEntities);
}
