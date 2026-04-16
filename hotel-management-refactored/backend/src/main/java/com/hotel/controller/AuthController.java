package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Admin;
import com.hotel.entity.User;
import com.hotel.service.AdminService;
import com.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器（登录、注册）
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AdminService adminService;

    /**
     * 用户登录
     */
    @PostMapping("/user/login")
    public Result<Map<String, Object>> userLogin(@RequestParam String userName,
                                                  @RequestParam String userPwd) {
        User user = userService.login(userName, userPwd);
        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getUserId());
        data.put("userName", user.getUserName());
        data.put("phone", user.getPhone());
        data.put("email", user.getEmail());
        return Result.success("登录成功", data);
    }

    /**
     * 用户注册
     */
    @PostMapping("/user/register")
    public Result<Void> userRegister(@Valid @RequestBody User user) {
        boolean success = userService.register(user);
        if (success) {
            return Result.success("注册成功");
        } else {
            return Result.error("注册失败");
        }
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping("/user/check")
    public Result<Boolean> checkUserName(@RequestParam String userName) {
        boolean exists = userService.checkUserNameExists(userName);
        return Result.success(exists);
    }

    /**
     * 管理员登录
     */
    @PostMapping("/admin/login")
    public Result<Map<String, Object>> adminLogin(@RequestParam String adminName,
                                                   @RequestParam String adminPwd) {
        Admin admin = adminService.login(adminName, adminPwd);
        Map<String, Object> data = new HashMap<>();
        data.put("adminId", admin.getAdminId());
        data.put("adminName", admin.getAdminName());
        return Result.success("登录成功", data);
    }
}
