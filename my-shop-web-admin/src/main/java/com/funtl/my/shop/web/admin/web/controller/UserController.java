package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄明潘
 * @date 2019/7/25-15:43
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;
        System.out.println(id);
        // id 不为空，则从数据库获取
        if (id != null) {
            tbUser = tbUserService.getById(id);
        } else {
            tbUser = new TbUser();
            System.out.println("#########################################################"+tbUser.toString());
        }

        return tbUser;
    }


    @GetMapping("list")
    public String list(Model model) {
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }


    @GetMapping("form")
    public String form() {
        return "user_form";
    }
    @PostMapping("save")
    public String save(TbUser tbUser,Model model,
                       RedirectAttributes redirectAttributes) {
        System.out.println(tbUser.toString());
        BaseResult baseResult = tbUserService.save(tbUser);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }

    }

    @PostMapping("search")
    public String search(String keyword,Model model) {
        List<TbUser> tbUsers = tbUserService.search(keyword);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    @ResponseBody
    @PostMapping("delete")
    public BaseResult delete(String ids) {
        BaseResult baseResult;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户成功");
        } else {
            baseResult = BaseResult.fail("删除用户失败");
        }

        return baseResult;
    }

    @ResponseBody
    @PostMapping("page")
    public Map<String,Object> page(@RequestParam(defaultValue = "0") Integer draw,
                       @RequestParam(defaultValue = "0") Integer start,
                       @RequestParam(defaultValue = "10") Integer length) {
        List<TbUser> tbUsers = tbUserService.page(start, length);
        int total = tbUserService.userTotal();
        Map<String, Object> maps = new HashMap<>();
        maps.put("draw", draw);
        maps.put("recordsTotal", total);
        maps.put("recordsFiltered", total);
        maps.put("data", tbUsers);
        return maps;
    }
}
