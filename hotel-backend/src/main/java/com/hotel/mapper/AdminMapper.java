package com.hotel.mapper;

import com.hotel.entity.Admin;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AdminMapper {

    Admin selectByUsernameAndPassword(@Param("adminName") String adminName, @Param("adminPwd") String adminPwd);

    int insert(Admin admin);

    int updateByPrimaryKey(Admin admin);

    int deleteByPrimaryKey(Long adminId);

    Admin selectByPrimaryKey(Long adminId);

    List<Admin> selectAll();
}
