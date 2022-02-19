package com.chenyue.blog.controller.admin;

import com.chenyue.blog.entity.Notice;
import com.chenyue.blog.enums.CodeEnum;
import com.chenyue.blog.enums.NoticeStatus;
import com.chenyue.blog.service.NoticeService;
import com.chenyue.blog.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @ResponseBody
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public ResponseVo insertNoticeSubmit(@RequestBody Notice notice) {
        notice.setNoticeCreateTime(LocalDateTime.now());
        notice.setNoticeUpdateTime(LocalDateTime.now());
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return new ResponseVo<>(CodeEnum.OK.code, CodeEnum.OK.message);
    }

    @ResponseBody
    @RequestMapping(value = "/getNotice")
    public ResponseVo<Notice> getNotice(@RequestParam Integer id) {
        return new ResponseVo<>(CodeEnum.OK.code,CodeEnum.OK.message, noticeService.getNoticeById(id));
    }
}
