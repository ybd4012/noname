package com.ybd.noname.controller.page;

import com.ybd.noname.entities.LogEntities;
import com.ybd.noname.service.LogService;
import com.ybd.noname.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yeah
 * @date 2020/7/29 15:26
 */
@Controller
@RequestMapping("/log")
public class LogPageController {
    @Autowired
    private LogServiceImpl logService;
    @GetMapping("/log")
    public String log(HttpServletRequest request){
        List<LogEntities> logAll = logService.getLogAll();
        request.setAttribute("logs",logAll);
        return "log/log";
    }
    @GetMapping("/add")
    public String toAdd(){
        return "redirect:/log/log-add.html";
    }
    @PostMapping("/addLog")
    public String addLog(LogEntities logEntities){
        System.out.println(logEntities);
        logService.addLog(logEntities);
        return "redirect:/log/log";
    }
}
