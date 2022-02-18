package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.CategoryDao;
import com.chenyue.blog.entity.Category;
import com.chenyue.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;


    @Override
    public Category getCategoryById(Integer id) {
        return categoryDao.queryCategoryById(id);
    }
}
