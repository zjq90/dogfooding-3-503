package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Admin;
import com.hotel.entity.User;
import com.hotel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/admin/login")
    public Result<Map<String, Object>> adminLogin(@RequestBody Admin admin) {
        Admin loginAdmin = loginService.adminLogin(admin.getAdminName(), admin.getAdminPwd());
        if (loginAdmin != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("admin", loginAdmin);
            return Result.success("登录成功", data);
        }
        return Result.error("账号或密码错误");
    }

    @PostMapping("/user/login")
    public Result<Map<String, Object>> userLogin(@RequestBody User user) {
        User loginUser = loginService.userLogin(user.getUserName(), user.getUserPwd());
        if (loginUser != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("user", loginUser);
            return Result.success("登录成功", data);
        }
        return Result.error("账号或密码错误");
    }

    @PostMapping("/user/register")
    public Result<String> register(@RequestBody User user) {
        if (loginService.checkUserNameExists(user.getUserName())) {
            return Result.error("用户名已存在");
        }
        if (loginService.register(user)) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }
}
