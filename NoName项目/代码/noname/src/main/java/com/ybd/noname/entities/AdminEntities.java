package com.ybd.noname.entities;

import lombok.Data;
import lombok.Getter;

import javax.management.relation.Role;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yeah
 * @date 2020/7/30 17:11
 */
@Data
@Getter
public class AdminEntities implements Serializable {
    /**
     * ad_id           int          (NULL)           NO      PRI     (NULL)             auto_increment                                 select,insert,update,references  唯一主键
     * ad_username     varchar(64)  utf8_general_ci  YES             (NULL)                                                            select,insert,update,references  登录管理员名
     * ad_psaaword     varchar(64)  utf8_general_ci  YES             (NULL)                                                            select,insert,update,references  登录密码
     * ad_role_id      int          (NULL)           NO      MUL     (NULL)                                                            select,insert,update,references  管理员所拥有的权限
     * ad_status       int          (NULL)           YES             1                                                                 select,insert,update,references  管理员信息的逻辑删除
     * ad_creat_time   timestamp    (NULL)           YES             CURRENT_TIMESTAMP  DEFAULT_GENERATED                              select,insert,update,references  管理员创建的时间
     * ad_update_time  timestamp    (NULL)           YES             CURRENT_TIMESTAMP  DEFAULT_GENERATED on update CURRENT_TIMESTAMP  select,insert,update,references  管理员信息的更新时间
     * ad_phone        varchar(16)  utf8_general_ci  YES             +8612345678900                                                    select,insert,update,references  管理员的手机号码
     * ad_email        varchar(64)  utf8_general_ci  YES             314817101@qq.com
     */
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String phone;
    private String email;

    private RoleEntities roleEntities;

}
