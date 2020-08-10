package com.ybd.noname.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;

/**
 * @author yeah
 * @date 2020/7/27 19:19
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // 登录成功后 重定向到main.html 利用thymeleaf模板解析器 解析到templates下的main.html页面
        registry.addViewController("/main.html").setViewName("main");

        // 会员列表页面重定向
//        registry.addViewController("/vip/member-list.html").setViewName("/vip/member-list");
        // 统计页面重定向
        registry.addViewController("/vip/member-count.html").setViewName("vip/member-count");
        // 会员添加页面重定向
        registry.addViewController("/vip/member-add.html").setViewName("vip/member-add");
        // 已经删除的会员列表页面重定向
        registry.addViewController("/vip/member-del.html").setViewName("vip/member-del");
        // 修改密码列表页面重定向
        registry.addViewController("/vip/member-password.html").setViewName("vip/member-password");
        // 编辑会员页面重定向
        registry.addViewController("/vip/member-edit.html").setViewName("vip/member-edit");

        // 订单列表页面重定向
        registry.addViewController("/order/order-list.html").setViewName("order/order-list");
        // 添加订单页面重定向
        registry.addViewController("/order/order-add.html").setViewName("order/order-add");

        // 分类页面重定向
        registry.addViewController("/categorize/cate-main.html").setViewName("categorize/cate-main");

        // 管理员-列表页面重定向
        registry.addViewController("/admin/admin-list.html").setViewName("admin/admin-list");
        // 管理员-角色页面重定向
        registry.addViewController("/admin/admin-role.html").setViewName("admin/admin-role");

        // 系统状态页面重定向
        registry.addViewController("/system/system-status.html").setViewName("system/system-status");

        // 日志页面重定向
        registry.addViewController("/log/log.html").setViewName("log/log");
        registry.addViewController("/log/log-add.html").setViewName("log/log-add");

        // 我的桌面重定向
        registry.addViewController("/welcome/welcome.html").setViewName("welcome/welcome");

        // 成功页面重定向
        registry.addViewController("/success.html").setViewName("success");

        //登录页面的重定向
        registry.addViewController("/login/login.html").setViewName("login/login");

    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new MyWebMvcConfig();
    }
    //将组件加入到容器中
        @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor(){
        }).excludePathPatterns("/","/vip/**","/css/**","/webjars/**","/dist/**","/fonts/**","/images/**","/js/**","/layui/**","/lib/**","/src/**");
    }
}
