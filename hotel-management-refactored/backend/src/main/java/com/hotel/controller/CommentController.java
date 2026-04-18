package com.hotel.controller;

import com.hotel.common.PageQuery;
import com.hotel.common.PageResult;
import com.hotel.common.Result;
import com.hotel.entity.Comment;
import com.hotel.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 评论控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 获取所有评论
     */
    @GetMapping("/list")
    public Result<List<Comment>> list() {
        List<Comment> list = commentService.list();
        return Result.success(list);
    }

    /**
     * 分页查询评论
     */
    @GetMapping("/page")
    public Result<PageResult<Comment>> page(PageQuery query) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Comment> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getCurrent(), query.getSize());
        com.baomidou.mybatisplus.core.metadata.IPage<Comment> page = commentService.page(mpPage);
        PageResult<Comment> result = new PageResult<>(
                page.getTotal(),
                page.getRecords(),
                page.getCurrent(),
                page.getSize()
        );
        return Result.success(result);
    }

    /**
     * 根据房间ID获取评论
     */
    @GetMapping("/room/{roomId}")
    public Result<List<Comment>> getByRoomId(@PathVariable String roomId) {
        List<Comment> list = commentService.getCommentsByRoomId(roomId);
        return Result.success(list);
    }

    /**
     * 根据用户名获取评论
     */
    @GetMapping("/user/{userName}")
    public Result<List<Comment>> getByUserName(@PathVariable String userName) {
        List<Comment> list = commentService.getCommentsByUserName(userName);
        return Result.success(list);
    }

    /**
     * 发表评论
     */
    @PostMapping
    public Result<Void> save(@Valid @RequestBody Comment comment) {
        boolean success = commentService.save(comment);
        if (success) {
            return Result.success("评论成功");
        } else {
            return Result.error("评论失败");
        }
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/{serNum}")
    public Result<Void> delete(@PathVariable Integer serNum) {
        boolean success = commentService.removeById(serNum);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
