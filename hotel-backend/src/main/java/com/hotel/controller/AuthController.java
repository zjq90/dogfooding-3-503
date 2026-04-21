package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Admin;
import com.hotel.entity.User;
import com.hotel.service.AdminService;
import com.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AdminService adminService;
    private final UserService userService;

    @PostMapping("/admin/login")
    public Result<Map<String, Object>> adminLogin(@RequestBody @Valid Admin admin, HttpSession session) {
        Admin adminLogin = adminService.login(admin.getAdminName(), admin.getAdminPwd());
        Map<String, Object> data = new HashMap<>();
        if (adminLogin != null) {
            session.setAttribute("admin", adminLogin);
            data.put("admin", adminLogin);
            data.put("token", session.getId());
            return Result.success("登录成功", data);
        } else {
            return Result.error("账号或密码错误");
        }
    }

    @PostMapping("/user/login")
    public Result<Map<String, Object>> userLogin(@RequestBody @Valid User user, HttpSession session) {
        User userLogin = userService.login(user.getUserName(), user.getUserPwd());
        Map<String, Object> data = new HashMap<>();
        if (userLogin != null) {
            session.setAttribute("user", userLogin);
            data.put("user", userLogin);
            data.put("token", session.getId());
            return Result.success("登录成功", data);
        } else {
            return Result.error("账号或密码错误");
        }
    }

    @PostMapping("/user/register")
    public Result<String> userRegister(@RequestBody @Valid User user) {
        boolean success = userService.register(user);
        if (success) {
            return Result.success("注册成功");
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/admin/logout")
    public Result<String> adminLogout(HttpSession session) {
        session.removeAttribute("admin");
        session.invalidate();
        return Result.success("退出成功");
    }

    @PostMapping("/user/logout")
    public Result<String> userLogout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return Result.success("退出成功");
    }
}
