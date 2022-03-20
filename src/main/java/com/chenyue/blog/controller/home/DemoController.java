package com.chenyue.blog.controller.home;

import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.service.CategoryService;
import com.chenyue.blog.service.UserService;
import com.chenyue.blog.vo.Response;
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
    public Response index(){
        // userService.testTransactional();
        return Response.OK();
    }

    @ResponseBody
    @RequestMapping("/getCategory")
    public Response getCategory(@RequestParam("id") Integer id) {
        Assert.notNull(id, "参数不能空");
        return Response.builder()
                .code(CodeEnum.OK.code)
                .msg( CodeEnum.OK.message)
                .data(categoryService.getCategoryById(id))
                .build();
    }

}
