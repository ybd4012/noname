package com.ybd.noname.component;

import com.ybd.noname.entities.AdminEntities;
import com.ybd.noname.entities.RoleEntities;
import com.ybd.noname.service.AdminService;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yeah
 * @date 2020/8/2 19:37
 *
 * 继承 AuthorizingRealm 重写  AuthorizationInfo（授权） AuthenticationInfo（认证）
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;
    /**
     * 重写授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("UserRealm———————————————————————授权");
        // 创建一个简单的授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取传入的对象、  getPrincipal
        AdminEntities adminEntities =(AdminEntities) SecurityUtils.getSubject().getPrincipal();
        System.out.println(adminEntities);
        // 拿到对象的权限、并且授权
        simpleAuthorizationInfo.addStringPermission(adminEntities.getRoleEntities().getPerms());
        // 返回设置好的授权规则
        return simpleAuthorizationInfo;
    }
    /**
     * 重写认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("UserRealm———————————————————————认证");
        // 拿到传入的数据
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 根据用户名查询是否存在
        AdminEntities adminEntities = new AdminEntities();
        adminEntities.setUsername(token.getUsername());
        AdminEntities adminByName = adminService.getAdminByName(adminEntities);
        System.out.println(adminByName);
        // 如果用户不存在、返回null 抛出用户名不存在异常
        if(adminByName == null){
            return null;
        }
        return new SimpleAuthenticationInfo(adminByName,adminByName.getPassword(),"");
    }
}
