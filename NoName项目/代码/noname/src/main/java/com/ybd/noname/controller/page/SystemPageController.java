package com.ybd.noname.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author yeah
 * @date 2020/7/29 15:52
 */
@Controller
@RequestMapping("/system")
public class SystemPageController {

    // TODO 系统状态展示
    /**
     * 系统状态展示
     * @param session
     * @return
     */
    @GetMapping("/status")
    public String status(HttpSession session){
        return "redirect:/system/system-status.html";
    }
}
