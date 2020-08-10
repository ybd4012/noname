package com.ybd.noname.controller;

import com.ybd.noname.entities.WelcomeDataEntities;
import com.ybd.noname.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yeah
 * @date 2020/8/8 14:48
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/showData")
    public String show(HttpServletRequest request){
        // 固定传入一个参数 作为缓存的 key
        WelcomeDataEntities welcomeDataEntities = welcomeService.showData(1);
        request.setAttribute("welcomeDataEntities",welcomeDataEntities);
        return "welcome/welcome";
    }
    @GetMapping("/updateWelcomeData")
    public String updateWelcomeData(HttpServletRequest request){
        // 固定传入一个参数 作为缓存的 key
        WelcomeDataEntities welcomeDataEntities = welcomeService.updateWelcomeData(1);
        request.setAttribute("welcomeDataEntities",welcomeDataEntities);
        return "welcome/welcome";
    }

}
