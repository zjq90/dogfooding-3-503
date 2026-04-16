package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Notice;
import com.hotel.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public Result<List<Notice>> getAllNotices() {
        List<Notice> notices = noticeService.findAll();
        return Result.success(notices);
    }

    @PostMapping("/add")
    public Result<String> addNotice(@RequestBody Notice notice) {
        if (noticeService.addNotice(notice)) {
            return Result.success("发布成功");
        }
        return Result.error("发布失败");
    }

    @DeleteMapping("/delete/{serNum}")
    public Result<String> deleteNotice(@PathVariable Integer serNum) {
        if (noticeService.deleteNotice(serNum)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
