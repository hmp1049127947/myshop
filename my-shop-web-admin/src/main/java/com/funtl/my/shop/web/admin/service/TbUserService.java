package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.PageInfo;
import com.funtl.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserService {
    /**
     * 查找所有的用户信息
     * @return
     */
    List<TbUser> selectAll();
    /**
     * 登陆
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);
    /**
     * 新增用户
     * @param tbUser
     * @return
     */
    BaseResult save(TbUser tbUser);

    TbUser getById(Long id);

    List<TbUser> search(String keyword);

    void deleteMulti(String[] ids);

   PageInfo<TbUser> page(int start, int length, int draw);

    int userTotal();
}