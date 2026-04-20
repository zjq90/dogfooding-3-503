package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Comment;
import com.hotel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list/{roomId}")
    public Result<List<Comment>> getCommentsByRoomId(@PathVariable String roomId) {
        List<Comment> comments = commentService.findByRoomId(roomId);
        return Result.success(comments);
    }

    @PostMapping("/add")
    public Result<String> addComment(@RequestBody Comment comment) {
        if (commentService.addComment(comment)) {
            return Result.success("评论成功");
        }
        return Result.error("评论失败");
    }

    @PutMapping("/update")
    public Result<String> updateComment(@RequestBody Comment comment) {
        if (commentService.updateComment(comment)) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    @DeleteMapping("/delete/{serNum}")
    public Result<String> deleteComment(@PathVariable Integer serNum) {
        if (commentService.deleteComment(serNum)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
