package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名和密码查询用户
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return 用户信息
     */
    @Select("SELECT * FROM t_user WHERE user_name = #{userName} AND user_pwd = #{userPwd}")
    User selectByNameAndPwd(@Param("userName") String userName, @Param("userPwd") String userPwd);

    /**
     * 根据用户名查询用户数量
     *
     * @param userName 用户名
     * @return 用户数量
     */
    @Select("SELECT COUNT(*) FROM t_user WHERE user_name = #{userName}")
    Integer countByUserName(@Param("userName") String userName);
}
