package com.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hotel.common.PageQuery;
import com.hotel.common.PageResult;
import com.hotel.common.Result;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public Result<List<User>> list() {
        List<User> list = userService.list();
        return Result.success(list);
    }

    /**
     * 分页查询用户
     */
    @GetMapping("/page")
    public Result<PageResult<User>> page(PageQuery query) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getCurrent(), query.getSize());
        com.baomidou.mybatisplus.core.metadata.IPage<User> page = userService.page(mpPage);
        PageResult<User> result = new PageResult<>(
                page.getTotal(),
                page.getRecords(),
                page.getCurrent(),
                page.getSize()
        );
        return Result.success(result);
    }

    /**
     * 根据ID获取用户
     */
    @GetMapping("/{userId}")
    public Result<User> getById(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        // 不返回密码
        user.setUserPwd(null);
        return Result.success(user);
    }

    /**
     * 根据用户名获取用户
     */
    @GetMapping("/name/{userName}")
    public Result<User> getByName(@PathVariable String userName) {
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", userName));
        if (user == null) {
            return Result.error("用户不存在");
        }
        user.setUserPwd(null);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/{userId}")
    public Result<Void> update(@PathVariable Integer userId, @Valid @RequestBody User user) {
        user.setUserId(userId);
        // 不允许通过此接口修改密码
        user.setUserPwd(null);
        boolean success = userService.updateById(user);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userName}")
    public Result<Void> delete(@PathVariable String userName) {
        boolean success = userService.remove(new QueryWrapper<User>().eq("user_name", userName));
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}
