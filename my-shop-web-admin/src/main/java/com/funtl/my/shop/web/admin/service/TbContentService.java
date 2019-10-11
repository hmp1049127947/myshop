package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/10/11-13:46
 */
public interface TbContentService {

    List<TbContent> selectAll();


    BaseResult save(TbContent tbContent);

    TbContent getById(Long id);

    List<TbContent> search(String keyword);

    void deleteMulti(String[] ids);

    PageInfo<TbContent> page(int start, int length, int draw);

    int contentTotal();
}
