package com.chenyue.blog.service;

import com.chenyue.blog.entity.Menu;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public interface MenuService {
    List<Menu> listMenu();
    Menu insertMenu(Menu menu);
    void deleteMenu(Integer menuId);
    void updateMenu(Menu menu);
    Menu getMenuById(Integer id);
}
