package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Notice;
import com.hotel.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public Result<List<Notice>> getNoticeList() {
        List<Notice> list = noticeService.getAllNotices();
        return Result.success(list);
    }

    @GetMapping("/latest")
    public Result<List<Notice>> getLatestNotices() {
        List<Notice> list = noticeService.getLatestNotices();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Notice> getNoticeById(@PathVariable Long id) {
        Notice notice = noticeService.getNoticeById(id);
        return Result.success(notice);
    }

    @PostMapping("/add")
    public Result<String> addNotice(@RequestBody Notice notice) {
        boolean success = noticeService.addNotice(notice);
        return success ? Result.success("发布成功") : Result.error("发布失败");
    }

    @PutMapping("/update")
    public Result<String> updateNotice(@RequestBody Notice notice) {
        boolean success = noticeService.updateNotice(notice);
        return success ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteNotice(@PathVariable Long id) {
        boolean success = noticeService.deleteNotice(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}
