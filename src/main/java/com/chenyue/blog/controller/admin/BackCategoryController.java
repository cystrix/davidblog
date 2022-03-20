package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Category;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/21
 * @description:
 */
@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public ModelAndView categoryList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("Admin/Category/index");
        return modelAndView;
    }

    @PostMapping("/insertSubmit")
    public String insertCategorySubmit(Category category) { // 接受的x-www-urlencoded
        category.setCategoryOrder(1);
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping("/delete/{id}")
    public String delCategory(@PathVariable("id") Integer id) {
        int count = articleService.countArticleByCategoryId(id);
        if (count == 0) {
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editCategoryView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.getCategoryById(id);
        modelAndView.addObject("category", category);
        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("Admin/Category/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCategorySubmit(Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }

}
