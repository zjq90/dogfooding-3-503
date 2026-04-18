package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Notice;
import com.hotel.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 公告控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 获取所有公告
     */
    @GetMapping("/list")
    public Result<List<Notice>> list() {
        List<Notice> list = noticeService.list();
        return Result.success(list);
    }

    /**
     * 获取最新公告
     */
    @GetMapping("/latest")
    public Result<List<Notice>> getLatest(@RequestParam(defaultValue = "5") int limit) {
        List<Notice> list = noticeService.getLatestNotices(limit);
        return Result.success(list);
    }

    /**
     * 根据ID获取公告
     */
    @GetMapping("/{serNum}")
    public Result<Notice> getById(@PathVariable Integer serNum) {
        Notice notice = noticeService.getById(serNum);
        if (notice == null) {
            return Result.error("公告不存在");
        }
        return Result.success(notice);
    }

    /**
     * 发布公告
     */
    @PostMapping
    public Result<Void> save(@Valid @RequestBody Notice notice) {
        boolean success = noticeService.save(notice);
        if (success) {
            return Result.success("发布成功");
        } else {
            return Result.error("发布失败");
        }
    }

    /**
     * 更新公告
     */
    @PutMapping("/{serNum}")
    public Result<Void> update(@PathVariable Integer serNum, @Valid @RequestBody Notice notice) {
        notice.setSerNum(serNum);
        boolean success = noticeService.updateById(notice);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 删除公告
     */
    @DeleteMapping("/{serNum}")
    public Result<Void> delete(@PathVariable Integer serNum) {
        boolean success = noticeService.removeById(serNum);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
