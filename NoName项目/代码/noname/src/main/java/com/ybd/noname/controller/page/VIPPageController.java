package com.ybd.noname.controller.page;

import com.ybd.noname.entities.VipEntities;
import com.ybd.noname.service.VipService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yeah
 * @date 2020/7/28 21:37
 */
@Controller
@RequestMapping(path = "/vip")
public class VIPPageController {
    @Autowired
    private VipService vipService;
    //TODO 没有为修改状态、添加用户、编辑用户、修改密码、删除 添加权限、后期应该添加

    /**
     * 跳转到统计图页面
     * @return
     */
    @GetMapping("/count")
    public String vipToCount(){
        return "redirect:/vip/member-count.html";
    }

    /**
     * 跳转到会员列表页面
     * 分页 每页10条数据
     * @return
     */
    @GetMapping("/list/{currentPage}")
    public String toList(HttpServletRequest request ,@PathVariable("currentPage")String currentPage){
        System.out.println(currentPage);

        // 获取总数据大小
        int size = vipService.getAllVip().size();

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

        System.out.println("limit="+startPage+","+pageSize);
        List<VipEntities> allVip = vipService.getAllVipByLimit(startPage,pageSize);

        request.setAttribute("allVip",allVip);
        return "vip/member-list";
    }

    /**
     * 编辑会员状态
     * @return
     */
    @GetMapping("/editStatus/{id}/{status}")
    public String editStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status){
        vipService.editStatusById(id,status);
        return "forward:/vip/list/0";
    }
    /**
     * 跳转添加页面
     * @return
     */
    @GetMapping("/add")
    public String toAdd(){
        return "redirect:/vip/member-add.html";
    }

    /**
     * 来到添加页面、添加成功后跳转到列表页面
     * @return
     */
    @PostMapping("/addVIPAndToList")
    public String addVIPAndToList(VipEntities vipEntities, HttpServletRequest request){
        // 设置默认值
        if (vipEntities.getName().equals("")){
            vipEntities.setName("none");
        }
        if (vipEntities.getPassword().equals("")){
            vipEntities.setPassword("123456");
        }
        if (String.valueOf(vipEntities.getGender()).equals("")){
            vipEntities.setGender(0);
        }
        if (vipEntities.getPhone().equals("")){
            vipEntities.setPhone("+8612345678900");
        }

        System.out.println("vip-add-info="+vipEntities);
        // 根据用户名查询是否已经存在
        VipEntities getOneByName = vipService.getOneByName(vipEntities.getUsername());
        System.out.println(getOneByName);
        // 存在 返回添加页面
        if (getOneByName != null){
            request.setAttribute("msg","用户已经存在");
            return "/vip/member-add";
        }
        // 不存在 添加进入数据库、返回列表
        else{
            vipService.addByDynamic(vipEntities);
            return "redirect:/vip/list/0";
        }
    }

    /**
     * 携带 id 跳转到编辑页面 然后查询出数据、保存到request中
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/edits/{id}")
    public String toEdit(@PathVariable("id") Integer id, HttpServletRequest request){
        // 根据id查询出数据
        VipEntities oneById = vipService.getOneById(id);
        System.out.println(oneById+"oneById");
        request.setAttribute("oneById",oneById);
        return "vip/member-edit";
    }

    /**
     * 跳转到编辑页面、编辑完后返回列表
     * @param
     * @return
     */
    @PostMapping("/editVIPAndToList")
    public String editVIPAndToList(VipEntities vipEntities){
        System.out.println(vipEntities);
        // 设置默认值
        if (vipEntities.getName().equals("")){
            vipEntities.setName(null);
        }
        if (vipEntities.getPassword().equals("")){
            vipEntities.setPassword(null);
        }
        if (String.valueOf(vipEntities.getGender()).equals("")){
            vipEntities.setGender(0);
        }
        if (vipEntities.getPhone().equals("")){
            vipEntities.setPhone(null);
        }
        System.out.println("编辑"+vipEntities);
        vipService.editVipByDynamic(vipEntities);
        return "redirect:/vip/list/0";
    }

    /**
     * 携带数据跳转到修改密码页面
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/password/{id}/{name}")
    public String toPassword(@PathVariable("id") Integer id, @PathVariable("name") String name, HttpServletRequest request){
        request.setAttribute("id",id);
        request.setAttribute("name",name);
        return "vip/member-password";
    }

    /**
     * 修改密码成功后、跳转到列表页面
     * @param vipEntities
     * @return
     */
    @PostMapping("/passwordVIPAndToList")
    public String passwordVIPAndToList(VipEntities vipEntities){
        System.out.println(vipEntities);
        vipService.editVipByDynamic(vipEntities);
        return "redirect:/vip/list/0";
    }

    /**
     *
     * @param username
     * @param request 返回的 id 作为缓存的 key
     * @return
     */
    @GetMapping("/find")
    public String findVipByUsername(@Param("username")String username, HttpServletRequest request){
        System.out.println(username);
        if (username.equals("") || username == null){
            request.setAttribute("msg","请输入条件");
            return "forward:/vip/list/0";
        }
        VipEntities oneByName = vipService.getOneByName(username);
        request.setAttribute("oneByName",oneByName);
        return "vip/member-list-findlist";
    }
}
