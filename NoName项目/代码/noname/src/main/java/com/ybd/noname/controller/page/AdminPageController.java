package com.ybd.noname.controller.page;

import com.ybd.noname.entities.AdminEntities;
import com.ybd.noname.entities.RoleEntities;
import com.ybd.noname.service.AdminService;
import com.ybd.noname.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * @author yeah
 * @date 2020/7/29 15:05
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;

    /**
     *
     * @param request
     * @param currentPage 访问的页数
     * @return
     */
    @GetMapping("/list/{currentPage}")
    public String list(HttpServletRequest request, @PathVariable("currentPage")String currentPage){
        System.out.println("currentPage="+currentPage);
        // 获取总数据大小
        int size = adminService.getAllAdmin().size();
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
        List<AdminEntities> allAdmin = adminService.getAllVipByLimit(startPage,pageSize);
        request.setAttribute("allAdmin",allAdmin);
        return "admin/admin-list";
    }

    /**
     * 角色权限管理页展示
     * @param request
     * @return
     */
    @GetMapping("/role")
    public String role(HttpServletRequest request){
        List<RoleEntities> allRole = roleService.getAllRole();
        request.setAttribute("allRole",allRole);
        return "admin/admin-role.html";
    }


    /**
     * 来到编辑界面
     * @return
     */
    @GetMapping("/admin-edit/{id}")
    public String adminEdit(@PathVariable("id") Integer id,HttpServletRequest request){
        request.setAttribute("editAdminId",id);
        return "admin/admin-edit";
    }
    /**
     * 来到编辑界面、编辑成功后返回会员列表
     * @return  返回编辑页面
     */
    @PostMapping("/admin-edit-submit")
    public String adminEditSubmit(AdminEntities adminEntities){
        System.out.println(adminEntities);
        adminService.editAdmin(adminEntities);
        return "redirect:/admin/list/0";
    }

    //TODO 修改为delete请求

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @GetMapping("/admin-delete/{id}")
    public String adminDelete(@PathVariable("id") Integer id){
        adminService.deleteById(id);
        return "redirect:/admin/list/0";
    }
    //TODO 修改为put请求
    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/admin-editStatus/{id}/{status}")
    public String adminEditStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status){
        adminService.editStatusById(id,status);
        return "redirect:/admin/list/0";
    }

    /**
     * 来到添加页面
     * @return
     */
    @GetMapping("/add")
    public String adminAdd(){
        return "admin/admin-add";
    }

    /**
     * 检查如果有就提醒重写输入、如果没有就添加进入数据库
     * @return
     */
    @PostMapping("/admin-add-check-name")
    public String adminAddCheckName(AdminEntities adminEntities, HttpServletRequest request){
        // 查询是否存在相同的用户
        AdminEntities getAdminByName = adminService.getAdminByName(adminEntities);
        System.out.println(getAdminByName);
        // 如果没有这个用户、就将输入的用户名保存、传入到前端、并且保存一条数据
        // TODO 这里可以考虑要不要对这条数据加行锁  for update
        if (getAdminByName==null){
            adminService.addAdminByDynamic(adminEntities);
            return "redirect:/success.html";
        }
        // TODO 错误应该将用户之前的输入的数据、放回在已经输入的 input 中
        request.setAttribute("adminCheckReturn","该用户名已经存在");
        return "admin/admin-add";
    }

    /**
     * 会发送 get请求、即使没有数值默认值为 ""
     * @param
     * @param request
     * @return
     */
    @GetMapping("/list-find-by-dynamic")
    public String findByDynamic(AdminEntities adminEntities, HttpServletRequest request){
        if (adminEntities.getUsername().equals("")){
            adminEntities.setUsername(null);
        }
        if (adminEntities.getEmail().equals("")){
            adminEntities.setEmail(null);
        }
        if (adminEntities.getPhone().equals("")){
            adminEntities.setPhone(null);
        }
        if(adminEntities.getUsername() == null && adminEntities.getEmail() == null && adminEntities.getPhone() == null){
            request.setAttribute("findPrompt","至少输入一个查询条件");
            return "forward:/admin/list/0";
        }
        List<AdminEntities> adminEntitiesList = adminService.getAdminByDynamic(adminEntities);
        request.setAttribute("adminEntitiesList",adminEntitiesList);
        return "admin/admin-list-findlist";
    }
}
