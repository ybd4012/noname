package com.ybd.noname.service.impl;

import com.ybd.noname.dao.LogDAO;
import com.ybd.noname.dao.VipDAO;
import com.ybd.noname.entities.*;
import com.ybd.noname.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * @author yeah
 * @date 2020/8/8 15:40
 */
@Service
public class WelcomeServiceImpl  implements WelcomeService {
    @Autowired
    private VipService vipService;
    @Autowired
    private LogService logService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private OrderService orderService;



    @Override
    @Cacheable(cacheNames = "welcome", key = "#key")
    public WelcomeDataEntities showData(Integer key) {
        return getData();
    }

    @Override
    @CachePut(cacheNames = "welcome", key = "#key")
    public WelcomeDataEntities updateWelcomeData(Integer key) {
        return getData();
    }

    /**
     * 通过查询数据库 给  welcomeDataEntities 赋值
     * @return
     */
    private WelcomeDataEntities getData(){
        int temp = 0;
        WelcomeDataEntities welcomeDataEntities = new WelcomeDataEntities();
        // 获取所有的vip
        List<VipEntities> allVip = vipService.getAllVip();
        // 取得数量
        welcomeDataEntities.setAllVipNums(String.valueOf(allVip.size()));
        // 遍历取得正在使用的 vip 数量
        Iterator<VipEntities> iterator = allVip.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getStatus()==1){
                temp++;
            }
        }
        // 设置正在使用的 vip 数量
        welcomeDataEntities.setVipUsingNums(String.valueOf(temp));
        // 获取所有的 管理员
        List<AdminEntities> allAdmin = adminService.getAllAdmin();
        Iterator<AdminEntities> iterator1 = allAdmin.iterator();
        temp = 0;
        while (iterator1.hasNext()){
            if(iterator1.next().getRoleEntities().getName().equals("超级管理员")){
                temp++;
            }
        }
        // 设置 超级管理员的数量
        welcomeDataEntities.setSupAdminNums(String.valueOf(temp));
        // 计算 然后设置出 普通管理员的数量
        welcomeDataEntities.setAdminNums(String.valueOf(allAdmin.size() - temp));
        // 获取日志数量 和 赋值
        List<LogEntities> logAll = logService.getLogAll();
        welcomeDataEntities.setLogNums(String.valueOf(logAll.size()));

        //设置订单数量
        welcomeDataEntities.setOrderNums(String.valueOf(orderService.getAllOrder().size()));

        //设置缓存到数据库时的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(System.currentTimeMillis());
        welcomeDataEntities.setLastUpdateTime(format);
        return welcomeDataEntities;
    }
}
