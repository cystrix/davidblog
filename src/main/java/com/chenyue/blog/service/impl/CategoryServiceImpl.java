package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.ArticleCategoryDao;
import com.chenyue.blog.dao.CategoryDao;
import com.chenyue.blog.entity.Category;
import com.chenyue.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    private ArticleCategoryDao articleCategoryDao;


    @Override
    public Integer countCategory() {
        return categoryDao.countCategory();
    }

    @Override
    public List<Category> listCategory() {
        return categoryDao.listCategory();
    }

    @Override
    public List<Category> listCategoryWithCount() {
        List<Category> categories = categoryDao.listCategory();
        for (Category c: categories) {
            Integer count = articleCategoryDao.countArticleByCategoryId(c.getCategoryId());
            c.setArticleCount(count);
        }
        return categories;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteCategory(Integer categoryId) {
        categoryDao.deleteCategoryById(categoryId);
        articleCategoryDao.deleteByCategoryId(categoryId);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public Category insertCategory(Category category) {
        categoryDao.insert(category);
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }
}
