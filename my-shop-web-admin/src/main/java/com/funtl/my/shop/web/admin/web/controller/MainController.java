package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄明潘
 * @date 2019/7/18-23:22
 */
@Controller
public class MainController {

    @RequestMapping(value = "main")
    public String main() {
        return "main";
    }

}
