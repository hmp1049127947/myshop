package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/10/11-0:40
 */
@Controller
@RequestMapping("content/category")
public class TbContentCategoryController {

    @Autowired
    private TbContentCategoryService categoryService;


    @GetMapping("/list")
    public String lists(Model model) {
        List<TbContentCategory> tbContentCategories = categoryService.queryAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        sortList(tbContentCategories,targetList,0L);
        model.addAttribute("tbContentCategories", targetList);
        return "TbContentCategory_list";
    }

    /**
     * 排序
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId 当前的父级类目 ID
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory sourceContentCategory : sourceList) {
            //如果有父节点则添加到集合中
            if (sourceContentCategory.getParentId().equals(parentId)) {
                targetList.add(sourceContentCategory);

                //如果有子节点，则添加到集合中
                if (sourceContentCategory.getIsParent()) {
                    for (TbContentCategory tbContentCategory : sourceList) {
                        if (tbContentCategory.getParentId().equals(sourceContentCategory.getId())) {
                            sortList(sourceList, targetList, sourceContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
