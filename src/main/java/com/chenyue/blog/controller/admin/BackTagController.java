package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Tag;
import com.chenyue.blog.service.ArticleService;
import com.chenyue.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/21
 * @description:
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<Tag> tagList = tagService.listTagWithCount();
        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("Admin/Tag/index");
        return modelAndView;
    }

    @PostMapping("/insertSubmit")
    public String insertTagSubmit(Tag tag) {
        tagService.insert(tag);
        return "redirect:/admin/tag";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id) {
        Integer count = articleService.countArticleByTagId(id);
        if (count == 0) {
            tagService.deleteById(id);
        }
        return "redirect:/admin/tag";
    }

    /**
     * 编辑标签页显示
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Tag tag = tagService.getTagById(id);
        modelAndView.addObject("tag", tag);
        List<Tag> tagList = tagService.listTagWithCount();
        modelAndView.addObject("tagList", tagList);
        modelAndView.setViewName("Admin/Tag/edit");
        return modelAndView;
    }

    /**
     * 编辑标签提交
     *
     * @param tag
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag)  {
        tagService.updateTag(tag);
        return "redirect:/admin/tag";
    }
}
