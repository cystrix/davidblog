package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Menu;
import com.chenyue.blog.enums.MenuLevel;
import com.chenyue.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/3/22
 * @description:
 */
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping
    public String menuList(Model model) {
        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList", menuList);
        return "Admin/Menu/index";
    }

    @PostMapping("/insertSubmit")
    public String insertMenuSubmit(Menu menu) {
        if (menu.getMenuOrder() == null) {
            menu.setMenuOrder(MenuLevel.TOP_MENU.value);
        }
        menuService.insertMenu(menu);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id)  {
        menuService.deleteMenu(id);
        return "redirect:/admin/menu";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        Menu menu =  menuService.getMenuById(id);
        modelAndView.addObject("menu",menu);

        List<Menu> menuList = menuService.listMenu();
        modelAndView.addObject("menuList",menuList);

        modelAndView.setViewName("Admin/Menu/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu)  {
        menuService.updateMenu(menu);
        return "redirect:/admin/menu";
    }

}
