package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbUserDao {
    /**
     * 查找所有的用户信息
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 通过email查找用户
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

    /**
     * 新增用户
     * @param tbUser
     * @return 受影响的行
     */
    int insertTbUser(TbUser tbUser);

    /**
     * 更新用户
     * @param tbUser
     * @return 受影响的行
     */
    int updateTbUser(TbUser tbUser);
}