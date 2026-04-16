package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.common.BusinessException;
import com.hotel.entity.User;
import com.hotel.mapper.UserMapper;
import com.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 用户服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String userName, String userPwd) {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userPwd)) {
            throw new BusinessException("用户名或密码不能为空");
        }
        User user = baseMapper.selectByNameAndPwd(userName, userPwd);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        log.info("用户登录成功：{}", userName);
        return user;
    }

    @Override
    public boolean checkUserNameExists(String userName) {
        return baseMapper.countByUserName(userName) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(User user) {
        if (checkUserNameExists(user.getUserName())) {
            throw new BusinessException("用户名已存在");
        }
        boolean success = save(user);
        if (success) {
            log.info("用户注册成功：{}", user.getUserName());
        }
        return success;
    }
}
