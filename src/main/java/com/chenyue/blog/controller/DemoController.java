package com.chenyue.blog.controller;

import com.chenyue.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    @Autowired
    UserService userService;


}
