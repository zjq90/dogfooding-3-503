package com.hotel.service;

import com.hotel.entity.User;
import java.util.List;

public interface UserService {

    User login(String userName, String userPwd);

    boolean register(User user);

    boolean checkUsernameExists(String userName);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(Long userId);

    User getUserById(Long userId);

    List<User> getAllUsers();

    List<User> getUsersByPage(int pageNum, int pageSize);

    int getUserCount();
}
