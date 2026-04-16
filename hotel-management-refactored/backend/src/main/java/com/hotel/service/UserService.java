package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.User;

/**
 * 用户服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return 用户信息
     */
    User login(String userName, String userPwd);

    /**
     * 检查用户名是否存在
     *
     * @param userName 用户名
     * @return true-存在，false-不存在
     */
    boolean checkUserNameExists(String userName);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return true-注册成功，false-注册失败
     */
    boolean register(User user);
}
