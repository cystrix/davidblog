package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Link;
import com.chenyue.blog.enums.ArticleStatus;
import com.chenyue.blog.enums.LinkStatus;
import com.chenyue.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/22
 * @description:
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping
    public ModelAndView linkList() {
        ModelAndView modelAndView = new ModelAndView();

        List<Link> linkList = linkService.listLink(null);

        modelAndView.addObject("linkList", linkList);

        modelAndView.setViewName("Admin/Link/index");
        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insertLinkView() {
        ModelAndView modelAndView = new ModelAndView();
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList", linkList);
        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }


    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertLinkSubmit(Link link) {
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(LinkStatus.NORMAL.value);
        linkService.insertLink(link);
        return "redirect:/admin/link/insert";
    }

    @RequestMapping("/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id) {
        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Link linkCustom = linkService.getLinkById(id);
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkCustom", linkCustom);

        modelAndView.addObject("linkList", linkList);
        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editLinkSubmit(Link link) {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }

}
