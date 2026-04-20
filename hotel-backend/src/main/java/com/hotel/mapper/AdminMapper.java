package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员数据访问层
 *
 * @author hotel-management
 */
public interface AdminMapper extends BaseMapper<Admin> {

    Admin selectByNameAndPassword(@Param("adminName") String adminName, @Param("adminPwd") String adminPwd);
}
