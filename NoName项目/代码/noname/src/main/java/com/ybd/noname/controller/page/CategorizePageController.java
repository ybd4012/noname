package com.ybd.noname.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author yeah
 * @date 2020/7/29 14:47
 */
@Controller
@RequestMapping(path = "/categorize")
public class CategorizePageController {
    @ResponseBody
    @GetMapping("/main")
    public String toMain(){
        return "这部分功能和其他模块的功能相差不是太多、所以就没写、";
//        return "redirect:/categorize/cate-main.html";
    }
}
