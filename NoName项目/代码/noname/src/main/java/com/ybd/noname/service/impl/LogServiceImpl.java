package com.ybd.noname.service.impl;

import com.ybd.noname.dao.LogDAO;
import com.ybd.noname.entities.LogEntities;
import com.ybd.noname.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yeah
 * @date 2020/7/30 13:25
 */
@Service
public class LogServiceImpl  implements LogService {
    @Autowired
    private LogDAO logDAO;
    @Override
    public LogEntities getLogById(Integer id) {
        return null;
    }
    @Override
    public List<LogEntities> getLogAll() {
        return logDAO.getLogAll();
    }

    @Override
    public void addLog(LogEntities logEntities) {
        logDAO.addLog(logEntities);
    }
}
