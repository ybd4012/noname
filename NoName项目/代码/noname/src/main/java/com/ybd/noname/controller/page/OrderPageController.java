package com.ybd.noname.controller.page;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.ybd.noname.entities.OrderEntities;
import com.ybd.noname.entities.VipEntities;
import com.ybd.noname.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * @author yeah
 * @date 2020/7/29 10:12
 */
@Controller
@RequestMapping(path = "/order")
public class OrderPageController {
    @Autowired
    private OrderService orderService;
    /**
     * 来到订单列表
     * @return
     */
    @GetMapping("/list/{currentPage}")
    public String orderList(HttpServletRequest request,@PathVariable("currentPage")String currentPage){

        System.out.println(currentPage);

        // 获取总数据大小
        int size = orderService.getAllOrder().size();

        System.out.println("数据大小："+size);
        String ifNextPage = "false";
        // 计算最大页数
        int pageSize = 10;
        int pageCount = size/pageSize;
        if((pageCount*pageSize)<size){
            pageCount++;
        }
        // 传入 sql 中 limit p1，p2（固定）  的 p1
        Integer  startPage = Integer.valueOf(currentPage)*pageSize;
        // 显示的总页数
        request.setAttribute("pageCount",pageCount);
        System.out.println("总页数"+pageCount);
        // 当前的页数 0 +1
        int currentPageInt = Integer.valueOf(currentPage)+1;
        System.out.println("当前页"+currentPageInt);
        request.setAttribute("currentPages",currentPageInt);

        // 下一页判断
        if (currentPageInt < pageCount) {
            ifNextPage = "true";
        }else {
            ifNextPage = "false";
        }
        request.setAttribute("nextPage",ifNextPage);
        System.out.println( "是否有下一页："+ifNextPage);
        //
//        List<VipEntities> allVip = vipService.getAllVip();
        System.out.println("limit="+startPage+","+pageSize);


        List<OrderEntities> allOrder = orderService.getAllVipByLimit(startPage,pageSize);
        request.setAttribute("allOrder",allOrder);
        return "order/order-list";
    }
    @GetMapping("/deleteOneById/{oderNumber}")
    public String deleteOneById(@PathVariable("oderNumber") String oderNumber){
        orderService.deleteOneByOderNumber(oderNumber);
        return "redirect:/order/list/0";
    }

    /**
     * 跳转到账单添加页面
     * @return
     */
    @GetMapping("/add/{id}/{name}/{phone}")
    public String orderAdd(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("phone") String phone, HttpServletRequest request){
        VipEntities vipEntities = new VipEntities();
        vipEntities.setId(id);
        vipEntities.setName(name);
        vipEntities.setPhone(phone);
        request.setAttribute("vipEntities",vipEntities);
        return "order/order-add";
    }

    /**
     * 来到账单添加页面添加完数据后返回订单页面
     * @return
     */
    @PostMapping("/addOrderAndToList")
    public String addOrderAndToList(OrderEntities orderEntities){
        Snowflake snowflake = IdUtil.getSnowflake(1, 2);
        //通过雪花算法添加唯一订单号
        orderEntities.setOderNumber(String.valueOf(snowflake.nextId()));
        System.out.println("添加的订单信息********"+orderEntities);

        orderService.addOrder(orderEntities);
        return "redirect:/order/list/0";
    }
    @GetMapping("/find")
    public String findVipByUsername(@Param("oderNumber")String oderNumber, HttpServletRequest request){
        System.out.println(oderNumber);
        if (oderNumber.equals("") || oderNumber == null){
            request.setAttribute("msg","请输入条件");
            return "forward:/order/list/0";
        }
        OrderEntities orderEntities =  orderService.getOneByoderNumber(oderNumber);
        request.setAttribute("allOrder",orderEntities);
        return "order/order-list-listfind";
    }
}
