package com.ybd.noname;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.ybd.noname.dao.LogDAO;
import com.ybd.noname.dao.OrderDAO;
import com.ybd.noname.dao.VipDAO;
import com.ybd.noname.entities.*;
import com.ybd.noname.service.AdminService;
import com.ybd.noname.service.LogService;
import com.ybd.noname.service.RoleService;
import com.ybd.noname.service.WelcomeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class NonameApplicationTests {
//    @Autowired
//    private LogDAO logDAO;
//    @Autowired
//    private LogService logService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private VipDAO vipDAO;
//    @Autowired
//    private OrderDAO orderDAO;
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private WelcomeService welcomeService;
////    @Test
////    void contextLoads() {
////        LogEntities logById = logDAO.getLogById(5);
////        System.out.println(logById);
////        System.out.println(logById.getCreate_time());
////    }
////    @Test
////    void contextLoads2() {
////        List<LogEntities> logAll = logDAO.getLogAll();
////        Iterator<LogEntities> iterator = logAll.iterator();
////        while (iterator.hasNext()){
////            System.out.println(iterator.next().toString());
////        }
////    }
//    @Test
//    void contextLoads3() {
//        List<LogEntities> logAll = logService.getLogAll();
//        Iterator<LogEntities> iterator = logAll.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }
//    }
//    @Test
//    void contextLoads4() {
//        LogEntities logEntities = new LogEntities();
//        logEntities.setContent("2222");
//        logService.addLog(logEntities);
//
//    }
//    @Test
//    void contextLoads5() {
//        List<RoleEntities> allRole = roleService.getAllRole();
//        Iterator<RoleEntities> iterator = allRole.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }
//    }
//    @Test
//    void contextLoads6() {
//        List<AdminEntities> allRole = adminService.getAllAdmin();
//        Iterator<AdminEntities> iterator = allRole.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }
//    }
//    @Test
//    void contextLoads7() {
//        AdminEntities adminEntities = new AdminEntities();
//        adminEntities.setUsername("admin");
//        System.out.println(adminEntities);
//        List<AdminEntities> allRole = adminService.getAdminByDynamic(adminEntities);
//        Iterator<AdminEntities> iterator = allRole.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }
//    }
//    @Test
//    void contextLoads8() {
//        AdminEntities adminEntities = new AdminEntities();
//        adminEntities.setUsername("admin");
//        AdminEntities adminByName = adminService.getAdminByName(adminEntities);
//        System.out.println(adminByName);
//    }
////    @Test
////    void contextLoads10() {
////        VipEntities oneById = vipDAO.getOneById(1);
////        System.out.println(oneById);
////    }
//    @Test
//    void contextLoads11() {
//        List<OrderEntities> oneById = orderDAO.getOneByIdJoint(4);
//        System.out.println(oneById);
//    }
//    @Test
//    void contextLoads13() {
//        OrderEntities oneById = orderDAO.getOneByoderNumber("1291297086574432256");
//        System.out.println(oneById);
//
//
//    }
//    @Test
//    void contextLoads12() {
//        List<VipEntities> allVip = vipDAO.getAllVip();
//        HashMap<String, List<VipEntities>> map = new HashMap<>(16);
//        map.put("ss",allVip);
//
//    }
//    @Test
//    void contextLoads14() {
//        VipEntities allVip = vipDAO.getOneById(1);
//        System.out.println(allVip);
//    }
//    @Test
//    void contextLoads15() {
////        String s = IdUtil.simpleUUID();
////        System.out.println(s);
//        for (int i = 0; i <10 ; i++) {
//            Snowflake snowflake = IdUtil.getSnowflake(2, 0);
//            long l = snowflake.nextId();
//            System.out.println(l);
//        }
//    }
//    @Test
//    void contextLoads16() {
//        Class<? extends DataSource> aClass = dataSource.getClass();
//        try {
//            Connection connection = dataSource.getConnection();
//            System.out.println(connection);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//    @Test
//    void contextLoads17() {
//        WelcomeDataEntities welcomeDataEntities = welcomeService.showData(3);
//        System.out.println(welcomeDataEntities);
//    }
//    @Test
//    void contextLoads18() {
//        int o = 14;
//        System.out.println("14"+o/10+"16"+(o+2)/10);
//    }
}
