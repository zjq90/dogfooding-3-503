package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 管理员数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据管理员名和密码查询管理员
     *
     * @param adminName 管理员名
     * @param adminPwd  密码
     * @return 管理员信息
     */
    @Select("SELECT * FROM t_admin WHERE admin_name = #{adminName} AND admin_pwd = #{adminPwd}")
    Admin selectByNameAndPwd(@Param("adminName") String adminName, @Param("adminPwd") String adminPwd);
}
