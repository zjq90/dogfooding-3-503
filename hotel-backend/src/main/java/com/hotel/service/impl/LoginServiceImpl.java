package com.hotel.service.impl;

import com.hotel.entity.Admin;
import com.hotel.entity.User;
import com.hotel.mapper.AdminMapper;
import com.hotel.mapper.UserMapper;
import com.hotel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务实现类
 *
 * @author hotel-management
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Admin adminLogin(String adminName, String adminPwd) {
        return adminMapper.selectByNameAndPassword(adminName, adminPwd);
    }

    @Override
    public User userLogin(String userName, String userPwd) {
        return userMapper.selectByNameAndPassword(userName, userPwd);
    }

    @Override
    public boolean register(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean checkUserNameExists(String userName) {
        return userMapper.countByUserName(userName) > 0;
    }
}
