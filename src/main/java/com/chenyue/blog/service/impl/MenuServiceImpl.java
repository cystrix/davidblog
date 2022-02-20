package com.chenyue.blog.service.impl;

import com.chenyue.blog.dao.MenuDao;
import com.chenyue.blog.entity.Menu;
import com.chenyue.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> listMenu() {
        return menuDao.listMenu();
    }

    @Override
    public Menu insertMenu(Menu menu) {
        menuDao.insert(menu);
        return menu;
    }

    @Override
    public void deleteMenu(Integer menuId) {
        menuDao.deleteById(menuId);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.update(menu);
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuDao.getMenuById(id);
    }
}
