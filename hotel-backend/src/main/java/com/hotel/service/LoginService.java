package com.hotel.service;

import com.hotel.entity.Admin;

/**
 * 登录服务接口
 *
 * @author hotel-management
 */
public interface LoginService {

    Admin adminLogin(String adminName, String adminPwd);

    com.hotel.entity.User userLogin(String userName, String userPwd);

    boolean register(com.hotel.entity.User user);

    boolean checkUserNameExists(String userName);
}
