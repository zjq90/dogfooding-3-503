package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.common.BusinessException;
import com.hotel.entity.Admin;
import com.hotel.mapper.AdminMapper;
import com.hotel.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 管理员服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin login(String adminName, String adminPwd) {
        if (!StringUtils.hasText(adminName) || !StringUtils.hasText(adminPwd)) {
            throw new BusinessException("用户名或密码不能为空");
        }
        Admin admin = baseMapper.selectByNameAndPwd(adminName, adminPwd);
        if (admin == null) {
            throw new BusinessException("用户名或密码错误");
        }
        log.info("管理员登录成功：{}", adminName);
        return admin;
    }
}
