package com.hotel.service.impl;

import com.hotel.entity.Admin;
import com.hotel.mapper.AdminMapper;
import com.hotel.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public Admin login(String adminName, String adminPwd) {
        return adminMapper.selectByUsernameAndPassword(adminName, adminPwd);
    }

    @Override
    public boolean addAdmin(Admin admin) {
        return adminMapper.insert(admin) > 0;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKey(admin) > 0;
    }

    @Override
    public boolean deleteAdmin(Long adminId) {
        return adminMapper.deleteByPrimaryKey(adminId) > 0;
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminMapper.selectAll();
    }
}
