package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/10/11-13:46
 */
@Repository
public interface TbContentDao {

    List<TbContent> selectAll();


    /**
     * 新增内容
     * @param tbContent
     */
    void insertTbContent(TbContent tbContent);

    /**
     * 更新内容
     * @param tbContent
     */
    void updateTbContent(TbContent tbContent);

    TbContent getById(Long id);

    List<TbContent> search(TbContent tbContent);

    /**
     * 多项删除
     * @param id
     */
    void deleteMulti(String[] id);

    /**
     * 分页查询，start/sql分页的左边参数 length/sql分页的右边参数
     * @param start
     * @param length
     * @return
     */
    List<TbContent> page(@Param("start") int start, @Param("length") int length);

    /**
     * 查询content的总数
     * @return
     */
    int contentTotal();
}
