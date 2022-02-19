package com.chenyue.blog.dao;

import com.chenyue.blog.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
@Repository
public interface MenuDao {
    int insert(Menu menu);
    int update(Menu menu);
    int deleteById(Integer id);

    Menu getMenuById(Integer id);
    List<Menu> listMenu();
}
