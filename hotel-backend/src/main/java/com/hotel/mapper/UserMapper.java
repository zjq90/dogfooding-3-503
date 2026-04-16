package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户数据访问层
 *
 * @author hotel-management
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByNameAndPassword(@Param("userName") String userName, @Param("userPwd") String userPwd);

    int countByUserName(@Param("userName") String userName);
}
