package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Admin;

/**
 * 管理员服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登录
     *
     * @param adminName 管理员名
     * @param adminPwd  密码
     * @return 管理员信息
     */
    Admin login(String adminName, String adminPwd);
}
