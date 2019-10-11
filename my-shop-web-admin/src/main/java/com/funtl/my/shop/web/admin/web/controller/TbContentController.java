package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/10/11-13:48
 */
@Controller
@RequestMapping("tbContent")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        System.out.println(id);
        // id 不为空，则从数据库获取
        if (id != null) {
            tbContent = tbContentService.getById(id);
        } else {
            tbContent = new TbContent();

        }
        return tbContent;
    }


    @GetMapping("list")
    public String list(Model model) {
        List<TbContent> tbContents = tbContentService.selectAll();
        model.addAttribute("tbContents", tbContents);
        return "content_list";
    }

    @GetMapping("form")
    public String form() {
        return "content_form";
    }

    @PostMapping("save")
    public String save(TbContent tbContent,Model model,
                       RedirectAttributes redirectAttributes) {
        System.out.println(tbContent.toString());
        BaseResult baseResult = tbContentService.save(tbContent);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }

    }


    @PostMapping("search")
    public String search(String keyword,Model model) {
        List<TbContent> tbContents = tbContentService.search(keyword);
        model.addAttribute("tbContents", tbContents);
        return "content_list";
    }


    @ResponseBody
    @PostMapping("delete")
    public BaseResult delete(String ids) {
        BaseResult baseResult;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        } else {
            baseResult = BaseResult.fail("删除内容失败");
        }

        return baseResult;
    }



    @ResponseBody
    @PostMapping("page")
    public PageInfo<TbContent> page(@RequestParam(defaultValue = "0") Integer draw,
                                 @RequestParam(defaultValue = "0") Integer start,
                                 @RequestParam(defaultValue = "10") Integer length) {

        return tbContentService.page(start,length,draw);
    }



}
