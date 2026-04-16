package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Message;
import com.hotel.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 留言控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    /**
     * 获取所有留言
     */
    @GetMapping("/list")
    public Result<List<Message>> list() {
        List<Message> list = messageService.list();
        return Result.success(list);
    }

    /**
     * 根据用户名获取留言
     */
    @GetMapping("/user/{userName}")
    public Result<List<Message>> getByUserName(@PathVariable String userName) {
        List<Message> list = messageService.getMessagesByUserName(userName);
        return Result.success(list);
    }

    /**
     * 发表留言
     */
    @PostMapping
    public Result<Void> save(@Valid @RequestBody Message message) {
        boolean success = messageService.save(message);
        if (success) {
            return Result.success("留言成功");
        } else {
            return Result.error("留言失败");
        }
    }

    /**
     * 删除留言
     */
    @DeleteMapping("/{serNum}")
    public Result<Void> delete(@PathVariable Long serNum) {
        boolean success = messageService.removeById(serNum);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
