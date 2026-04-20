package com.hotel.service;

import com.hotel.entity.User;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author hotel-management
 */
public interface UserService {

    List<User> findAll();

    User findById(Integer userId);

    boolean updateUser(User user);

    boolean deleteUser(Integer userId);
}
