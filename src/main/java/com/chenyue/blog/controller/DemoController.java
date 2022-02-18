package com.chenyue.blog.controller;

import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.service.CategoryService;
import com.chenyue.blog.service.UserService;
import com.chenyue.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/index")
    public ResponseVo index(){
        return new ResponseVo(200, "SUCCESS","欢迎");
    }

    @ResponseBody
    @RequestMapping("/getCategory")
    public ResponseVo getCategory(@RequestParam("id") Integer id) {
        Assert.notNull(id, "参数不能空");
        return new ResponseVo(CodeEnum.OK.code, CodeEnum.OK.message, categoryService.getCategoryById(id));
    }


}
