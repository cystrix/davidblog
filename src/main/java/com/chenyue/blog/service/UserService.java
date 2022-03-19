package com.chenyue.blog.service;



import com.chenyue.blog.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获得所有用户
     * @return 用户列表
     */
    List<User> listUser();

    User getUserById(Integer id);

    void update(User user);

    void deleteById(Integer id);

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

    boolean isUserExisted(String username);

    boolean isUserEmailExisted(String email);
}
