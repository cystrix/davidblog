package com.chenyue.blog.service;

import com.chenyue.blog.entity.Student;

import java.util.List;

public interface DemoService {
    void insert(Student student);
    List<Student> getAllUsers();
}
