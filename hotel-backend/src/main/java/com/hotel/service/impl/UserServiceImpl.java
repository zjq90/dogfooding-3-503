package com.hotel.service.impl;

import com.hotel.entity.User;
import com.hotel.mapper.UserMapper;
import com.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User login(String userName, String userPwd) {
        return userMapper.selectByUsernameAndPassword(userName, userPwd);
    }

    @Override
    public boolean register(User user) {
        if (checkUsernameExists(user.getUserName())) {
            return false;
        }
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean checkUsernameExists(String userName) {
        return userMapper.countByUsername(userName) > 0;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKey(user) > 0;
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userMapper.deleteByPrimaryKey(userId) > 0;
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getUsersByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return userMapper.selectByPage(offset, pageSize);
    }

    @Override
    public int getUserCount() {
        return userMapper.countAll();
    }
}
