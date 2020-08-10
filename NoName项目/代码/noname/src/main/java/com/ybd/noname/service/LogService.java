package com.ybd.noname.service;

import com.ybd.noname.entities.LogEntities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 13:20
 */
public interface LogService {
    /**
     * 通过 id 获取到一个日志
     * @param id
     * @return
     */
    LogEntities getLogById(Integer id);
    /**
     * 获取所有日志
     * @return
     */
    List<LogEntities> getLogAll();

    /**
     * 添加日志信息
     * @param logEntities
     */
    void addLog(LogEntities logEntities);
}
