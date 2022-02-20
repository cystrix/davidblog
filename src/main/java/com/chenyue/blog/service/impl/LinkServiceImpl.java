package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.LinkDao;
import com.chenyue.blog.entity.Link;
import com.chenyue.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public Integer countLink(Integer status) {
        return linkDao.countLink(status);
    }

    @Override
    public List<Link> listLink(Integer status) {
        return linkDao.listLink(status);
    }

    @Override
    public void insertLink(Link link) {
        linkDao.insert(link);
    }

    @Override
    public void deleteLink(Integer id) {
        linkDao.deleteById(id);
    }

    @Override
    public void updateLink(Link link) {
        linkDao.update(link);
    }

    @Override
    public Link getLinkById(Integer id) {
        return linkDao.getLinkById(id);
    }
}
