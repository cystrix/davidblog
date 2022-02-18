package com.chenyue.blog.controller;

import com.chenyue.blog.entity.Student;
import com.chenyue.blog.enums.ResponseCode;
import com.chenyue.blog.service.DemoService;
import com.chenyue.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/addOne", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseVo addOne(@RequestBody Student student){
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setIsDelete(0);
        demoService.insert(student);
        return new ResponseVo(ResponseCode.OK.code, ResponseCode.OK.message, "插入成功");
    }

    @RequestMapping("/getAllStudent")
    @ResponseBody
    public ResponseVo<List<Student>> getStudents(){
        return new ResponseVo(ResponseCode.OK.code, ResponseCode.OK.message, demoService.getAllUsers());
    }

    @RequestMapping(value = "/ajaxget", method = {RequestMethod.GET})
    @ResponseBody
    public  ResponseVo ajaxGet(@RequestParam Integer id){
        return new ResponseVo(ResponseCode.OK.code, ResponseCode.OK.message, "get 参数 id:"+ id);
    }

    @RequestMapping("/freemarker")
    public  ModelAndView freemarker(ModelMap modelMap) {
        ModelAndView mav = new ModelAndView("view/index");
        modelMap.addAttribute("uname", "陈越");
        modelMap.addAttribute("uage", "22");
        mav.addAllObjects(modelMap);
        return mav;
    }
}
