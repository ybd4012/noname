package com.ybd.noname.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yeah
 * @date 2020/8/4 15:20
 */
@Data
@ToString
public class VipEntities implements Serializable {
    /**
     * 唯一主键
     */
    private Integer id;
    /**
     * 登录名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别0:女性1:男性
     */
    private Integer gender;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * Email
     */
    private String email;
    /**
     * 创建时间
     */
    private Timestamp creat_time;
    /**
     * 信息发生改变的时间
     */
    private Timestamp update_time;
    /**
     * 态，1表示在用，0表示停用
     */
    private Integer status;
    /**
     * 姓名
     */
    private String name;

    //TODO 一个用户下有多个订单 这里应该还有一个字段（该字段不存在数据库中的vip中）
    /**
     * 一个用户多个订单
     */
    private List<OrderEntities> orderEntities;
}
