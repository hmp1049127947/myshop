package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;
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

    TbUser getByPhone(String phone);

    TbUser getByUsername(String username);

    /**
     * 新增用户
     * @param tbUser
     * @return 受影响的行
     */
    void insertTbUser(TbUser tbUser);

    /**
     * 更新用户
     * @param tbUser
     * @return
     */
    void updateTbUser(TbUser tbUser);

    TbUser getById(Long id);

    List<TbUser> search(TbUser tbUser);

    void deleteMulti(String[] id);

    /**
     * 分页查询，start/sql分页的左边参数 length/sql分页的右边参数
     * @param start
     * @param length
     * @return
     */
    List<TbUser> page(@Param("start") int start, @Param("length") int length);

    /**
     * 查询user的总数
     * @return
     */
    int userTotal();
}