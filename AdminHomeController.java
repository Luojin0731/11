package com.lj.mall.controller.admin;

import com.lj.mall.controller.BaseController;
import com.lj.mall.entity.Admin;
import com.lj.mall.service.AdminService;
import com.lj.mall.service.ProductOrderService;
import com.lj.mall.service.ProductService;
import com.lj.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;

@Controller
public class AdminHomeController extends BaseController {
    @Resource
    private AdminService adminService;
    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private ProductOrderService productOrderService;

	
//111

    //转到后台管理-主页
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object>map)throws ParseException{
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return "redirect:/admin/login";
        }
        logger.info("获取管理员信息");
        Admin admin = adminService.get(null,Integer.parseInt(adminId.toString()));
        map.put("admin",admin);

//222
        logger.info("获取统计信息");
        Integer productTotal = productService.getTotal(null,new Byte[]{0,2});
        Integer userTotal =userService.getTotal(null);
        Integer orderTotal = productOrderService.getTotal(null, new Byte[]{3});
        logger.info("获取图标信息");
//      map.put("jsonObject",null);
        map.put("productTotal",productTotal);
        map.put("userTotal",userTotal);
        map.put("orderTotal",orderTotal);
        logger.info("转到后台管理-主页");
//333
        return "admin/homePage";
    }


}
