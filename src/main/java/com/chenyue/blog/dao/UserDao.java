package com.chenyue.blog.dao;

import com.chenyue.blog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int insert(User user);
    User getUserById(Integer id);
    void update(User user);
    User getUserByNameOrEmail(String str);
    int deleteById(Integer userId);
    List<User> listUser();
    User getUserByName();
    User getUserByEmail();

}
