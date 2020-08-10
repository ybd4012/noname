package com.ybd.noname.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ybd.noname.component.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author yeah
 * @date 2020/8/2 20:22
 *
 * 1、创建User
 */
@Configuration
public class MyShiroConfig {
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return  defaultWebSecurityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 设置登录页面的请求  如果访问需要认证才可以访问的页面就会执行这个请求来到登录的页面提示登录
        shiroFilterFactoryBean.setLoginUrl("/");
        // 设置发送登录的请求  当用户名和密码正确的时候就发送这个请求、就可以访问需要认证的页面
        shiroFilterFactoryBean.setSuccessUrl("/loginMain");
        // 设置需要授权的请求  访问需要授权的页面的提示页面的请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/toUnauthorized");

        // 创建一个map来设置需要授权和认证的规则
        HashMap<String, String> map = new HashMap<>();
        // 五个规则
        /*
         * anon 无需认证
         * authc 需要认证
         * user 需要记住我功能
         * perms 对某个资源有访问权限
         * role 需要某个角色才可以访问
         */
        // 登录页面不需要认证
        map.put("/","anon");
        map.put("/index.html","anon");
        map.put("/index","anon");



        // 需要认证才可以访问的页面
        map.put("/main.html","authc");

        // 管理员页面下的请求
        map.put("/admin/*","authc");

        // 分类下的所有请求
        map.put("/categorize/*","authc");

        // 日志下的所有请求、主要包括日志展示和日志添加
        map.put("/log/*","authc");

        // 订单下的所有请求
        map.put("/order/*","authc");

        // 系统状态页面
        map.put("/system/*","authc");

        // vip页面下的子页面
        map.put("/vip/*","authc");

        // TODO 一些需要权限的就整合了 thymeleaf 模板进行了显示和隐藏 后续会持续的更新模板和授权规则

        map.put("/log/add","perms[admin:all]");

        map.put("/admin/add","perms[admin:all]");
        map.put("/admin/admin-delete/*","perms[admin:all]");
        map.put("/admin/admin-edit/*","perms[admin:all]");
        map.put("/admin/admin-editStatus/*/*","perms[admin:all]");

        map.put("/vip/add","perms[admin:all]");
        map.put("/vip/editStatus/*/*","perms[admin:all]");
        map.put("/vip/edits/*","perms[admin:all]");
        // 未补全 删除


        // 订单列表
        map.put("/order/deleteOneById/*","perms[admin:all]");
        // 设置规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 用于 shiro 整合 Thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
