package com.hotel.mapper;

import com.hotel.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {

    User selectByUsernameAndPassword(@Param("userName") String userName, @Param("userPwd") String userPwd);

    int countByUsername(@Param("userName") String userName);

    int insert(User user);

    int updateByPrimaryKey(User user);

    int deleteByPrimaryKey(Long userId);

    User selectByPrimaryKey(Long userId);

    List<User> selectAll();

    List<User> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();
}
