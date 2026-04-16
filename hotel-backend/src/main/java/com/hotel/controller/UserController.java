package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }

    @GetMapping("/detail/{userId}")
    public Result<User> getUserById(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }

    @PutMapping("/update")
    public Result<String> updateUser(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    @DeleteMapping("/delete/{userId}")
    public Result<String> deleteUser(@PathVariable Integer userId) {
        if (userService.deleteUser(userId)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
