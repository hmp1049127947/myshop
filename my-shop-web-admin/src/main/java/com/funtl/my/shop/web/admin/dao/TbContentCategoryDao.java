package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/10/11-0:38
 */
@Repository
public interface TbContentCategoryDao {
    List<TbContentCategory> queryAll();
}
