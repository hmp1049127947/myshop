package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 黄明潘
 * @date 2019/10/11-13:46
 */
@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        return null;
    }

    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public List<TbContent> search(String keyword) {
        TbContent tbContent = new TbContent();
        tbContent.setTitle(keyword);
        tbContent.setSubTitle(keyword);
        tbContent.setTitleDesc(keyword);
        return tbContentDao.search(tbContent);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw) {
        PageInfo<TbContent> pageInfo = new PageInfo<>();
        pageInfo.setData(tbContentDao.page(start,length));
        pageInfo.setDraw(draw);
        int total = tbContentDao.contentTotal();
        pageInfo.setRecordsTotal(total);
        pageInfo.setRecordsFiltered(total);
        return pageInfo;
    }

    @Override
    public int contentTotal() {
        return tbContentDao.contentTotal();
    }


}
