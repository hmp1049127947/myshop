package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;


    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser != null) {
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            // 判断加密后的密码和数据库中存放的密码是否匹配，匹配则表示允许登录
            if (md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkData(tbUser);
        //数据验证成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());
            //新增用户
            if (tbUser.getId() == null) {
                // 密码需要加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(tbUser.getUpdated());
                tbUserDao.insertTbUser(tbUser);
            }
            //更新用户
            else {
                tbUserDao.updateTbUser(tbUser);
            }
            baseResult.setMessage("用户保存成功");
            return baseResult;
        }
        //数据验证失败
        else {
            return baseResult;
        }
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public List<TbUser> search(String keyword) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(keyword);
        tbUser.setPhone(keyword);
        tbUser.setEmail(keyword);
        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public List<TbUser> page(int start, int length) {
        return tbUserDao.page(start,length);
    }

    @Override
    public int userTotal() {
        return tbUserDao.userTotal();
    }

    public BaseResult checkData(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();
        //数据错误，密码或者账户名为空
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空");
            return baseResult;
        } else {
            //如果是新增用户，检查email是否已存在
            if (tbUser.getId() == null) {
                TbUser email = tbUserDao.getByEmail(tbUser.getEmail());
                if (email!=null) {
                    baseResult = BaseResult.fail("邮箱已存在，请检查后输入");
                    return baseResult;
                }
            }
        }
        if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空");
            return baseResult;
        }
        if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("姓名不能为空");
            return baseResult;
        } else {
            //如果是新增用户，检查username是否已存在
            if (tbUser.getId() == null) {
                TbUser username = tbUserDao.getByUsername(tbUser.getUsername());
                if (username!=null) {
                    baseResult = BaseResult.fail("该用户名已存在，请检查后输入");
                    return baseResult;
                }
            }
        }
        if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机不能为空");
            return baseResult;
        } else {
            //如果是新增用户，检查phone是否已存在
            if (tbUser.getId() == null) {
                TbUser phone = tbUserDao.getByPhone(tbUser.getPhone());
                if (phone!=null) {
                    baseResult = BaseResult.fail("该手机号已存在，请检查后输入");
                    return baseResult;
                }
            }
        }
        //数据正确
        return baseResult;
    }

}