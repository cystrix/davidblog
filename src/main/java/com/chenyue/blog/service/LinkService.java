package com.chenyue.blog.service;

import com.chenyue.blog.entity.Link;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface LinkService {
    Integer countLink(Integer status);
    List<Link> listLink(Integer status);
    void insertLink(Link link);
    void deleteLink(Integer id);
    void updateLink(Link link);
    Link getLinkById(Integer id);
}
