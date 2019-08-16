package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbUser;

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
    int save(TbUser tbUser);
}