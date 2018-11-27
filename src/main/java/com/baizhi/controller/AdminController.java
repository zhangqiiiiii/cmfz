package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService as;

    @RequestMapping("/login")
    public String login(Admin admin, Map map, HttpSession session, String number) {
        Admin a = as.login(admin);
        System.out.println("登录测试" + a);
        String sessionkaptcha = (String) session.getAttribute("kaptcha");
        if (a != null && sessionkaptcha.equalsIgnoreCase(number)) {
            map.put("admin", a);
            return "redirect:main/main.jsp";
        } else {
            return "login";
        }

    }
}
