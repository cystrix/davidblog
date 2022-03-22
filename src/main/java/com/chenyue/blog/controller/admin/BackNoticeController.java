package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Notice;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.enums.NoticeStatus;
import com.chenyue.blog.service.NoticeService;
import com.chenyue.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/19
 * @description:
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping
    public String index(Model model) {
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";
    }

    @RequestMapping("/insert")
    public String insertNoticeView() {
        return "Admin/Notice/insert";
    }

    @ResponseBody
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) {
        notice.setNoticeCreateTime(LocalDateTime.now());
        notice.setNoticeUpdateTime(LocalDateTime.now());
        notice.setNoticeStatus(NoticeStatus.NORMAL.value);
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice";
    }

    @RequestMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);
        return "redirect:/admin/notice";
    }

    @RequestMapping("/edit/{id}")
    public String editNoticeView(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "Admin/Notice/edit";
    }

    @RequestMapping("/editSubmit")
    public String editNoticeSubmit(Notice notice) {
        notice.setNoticeUpdateTime(LocalDateTime.now());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }

    @ResponseBody
    @RequestMapping(value = "/getNotice")
    public Response getNotice(@RequestParam Integer id) {
        return Response.builder().code(CodeEnum.OK.code).msg(CodeEnum.OK.message).data(noticeService.getNoticeById(id)).build();
    }
}
