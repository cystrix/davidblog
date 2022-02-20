package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.PageDao;
import com.chenyue.blog.entity.Page;
import com.chenyue.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageDao pageDao;

    @Override
    public List<Page> listPage(Integer status) {
        return pageDao.listPage(status);
    }

    @Override
    public Page getPageByKey(Integer status, String key) {
        return pageDao.getPageByKey(status, key);
    }

    @Override
    public Page getPageById(Integer id) {
        return pageDao.getPageById(id);
    }

    @Override
    public void insertPage(Page page) {
        pageDao.insert(page);
    }

    @Override
    public void deletePage(Integer id) {
        pageDao.deleteById(id);
    }

    @Override
    public void updatePage(Page page) {
        pageDao.update(page);
    }
}
