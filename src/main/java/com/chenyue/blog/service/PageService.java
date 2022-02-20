package com.chenyue.blog.service;

import com.chenyue.blog.entity.Page;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface PageService {
    List<Page> listPage(Integer status);
    Page getPageByKey(Integer status, String key);
    Page getPageById(Integer id);
    void insertPage(Page page);
    void deletePage(Integer id);
    void updatePage(Page page);
}
