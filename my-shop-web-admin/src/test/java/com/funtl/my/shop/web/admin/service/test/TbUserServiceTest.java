package com.funtl.my.shop.web.admin.service.test;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/7/23-17:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml", "classpath:spring-druid.xml", "classpath:spring-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private  TbUserService tbUserService;
    @Autowired
    private TbUserDao tbUserDao;
    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsertTbUser() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("yrk");
        tbUser.setUpdated(new Date());
        tbUser.setEmail("yrk@a.com");
        tbUser.setPhone("134895623");
        tbUser.setCreated(new Date());
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        int i = tbUserService.save(tbUser);
        if (i == 1) {
            System.out.println("测试成功");
        }
    }


}
