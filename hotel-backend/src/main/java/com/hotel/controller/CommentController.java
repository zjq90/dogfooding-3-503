package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Comment;
import com.hotel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getCommentList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Comment> list = commentService.getCommentsByPage(pageNum, pageSize);
        int total = commentService.getCommentCount();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("total", total);
        return Result.success(data);
    }

    @GetMapping("/room/{roomId}")
    public Result<List<Comment>> getCommentsByRoomId(@PathVariable String roomId) {
        List<Comment> list = commentService.getCommentsByRoomId(roomId);
        return Result.success(list);
    }

    @GetMapping("/user/{userName}")
    public Result<List<Comment>> getCommentsByUserName(@PathVariable String userName) {
        List<Comment> list = commentService.getCommentsByUserName(userName);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return Result.success(comment);
    }

    @PostMapping("/add")
    public Result<String> addComment(@RequestBody Comment comment) {
        boolean success = commentService.addComment(comment);
        return success ? Result.success("评论成功") : Result.error("评论失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteComment(@PathVariable Long id) {
        boolean success = commentService.deleteComment(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}
