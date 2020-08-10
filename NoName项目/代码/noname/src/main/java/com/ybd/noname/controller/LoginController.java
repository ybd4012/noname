package com.ybd.noname.controller;

import com.ybd.noname.entities.AdminEntities;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yeah
 * @date 2020/7/27 17:44
 */
@Controller
public class LoginController {
    /**
     * 首页设置成为登录页面
     * @return
     */
    @GetMapping({"/","/index","index.html"})
    public String toLoginPage(){
        return "login/login";
    }

    /**
     * 登录请求
     * @return
     */
    @PostMapping("/loginMain")
    public String loginMain(AdminEntities adminEntities, HttpServletRequest request , HttpSession session){
        System.out.println(adminEntities);
        // 将得到的数据 放入令牌中
        UsernamePasswordToken token = new UsernamePasswordToken(adminEntities.getUsername(),adminEntities.getPassword());
        // 获取一个subject
        Subject subject = SecurityUtils.getSubject();

        // 执行登录 这可能会出现用户名不存在、和密码错误的异常 需要处理
        try{
            subject.login(token);
            // 重定向到主页面、携带用户的部分信息
            session.setAttribute("usernameInfo",adminEntities.getUsername());
            return "redirect:main.html";
        }catch (UnknownAccountException e){
            // 用户名不存在异常、(请求转发)返回一个信息给页面
            request.setAttribute("msg","用户名不存在！");
            System.out.println("用户名不存在");
            return "login/login";
        }catch (IncorrectCredentialsException e){
            request.setAttribute("msg","密码错误！");
            System.out.println("密码错误");
            return "login/login";
        }
    }
    //TODO 跳转到我的桌面、被拦截项

    /**
     * 我的桌面
     * @return
     */
    @GetMapping("/toMain")
    public String toMain(){
        return "redirect:/welcome/welcome.html";
    }

    /**
     * 未授权发送的请求
     * @return
     */
    @RequestMapping("/toUnauthorized")
    @ResponseBody
    public String toUnauthorized(){
        return "权限不够、请联系管理员升级权限";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping(path = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userInfo");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
    @ResponseBody
    @GetMapping("/toFrontDesk")
    public String toFrontDesk(){
        return "开发中......";
    }
}
