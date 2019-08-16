package com.funtl.my.shop.web.admin.web.controller;


import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    @Autowired
    private TbUserService tbUserService;

    //登陆界面
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    private String login() {

        return "login";
    }
    //登陆请求
    @RequestMapping(value = "login",method = RequestMethod.POST)
    private String login(String email, String password, HttpServletRequest request, Model model) {
        TbUser tbUser = tbUserService.login(email, password);

        // 登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login();
        }

        // 登录成功
        else {
            // 将登录信息放入会话
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }

    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }

}
