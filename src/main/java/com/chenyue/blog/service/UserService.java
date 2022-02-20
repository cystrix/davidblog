package com.chenyue.blog.service;



import com.chenyue.blog.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获得所有用户
     * @return 用户列表
     */
    List<User> listUser();

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 更新一个用户
     * @param user
     */
    void update(User user);

    void deleteById(Integer id);

    /**
     * 添加一个用户
     * @param user
     * @return 影响的行数
     */
    int insert(User user);

    /**
     * 根据name或者email查找用户
     * @param str
     * @return
     */
    User getUserByNameOrEmail(String str);


    User getUserByName(String name);

    User getUserByEmail(String email);

    void testTransactional();
}
