package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Options;
import com.chenyue.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/22
 * @description:
 */
@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {
    @Autowired
    private OptionsService optionsService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Options options = optionsService.getOptions();
        modelAndView.addObject("option", options);
        modelAndView.setViewName("Admin/Options/index");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editOptionSubmit(Options options) {
        Options optionsCustom = optionsService.getOptions();
        if (optionsCustom == null) {
            optionsService.insertOptions(options);
        }else  {
            optionsService.updateOptions(options);
        }
        return "redirect:/admin/options";
    }
}
