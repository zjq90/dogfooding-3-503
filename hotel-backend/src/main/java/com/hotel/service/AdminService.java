package com.hotel.service;

import com.hotel.entity.Admin;
import java.util.List;

public interface AdminService {

    Admin login(String adminName, String adminPwd);

    boolean addAdmin(Admin admin);

    boolean updateAdmin(Admin admin);

    boolean deleteAdmin(Long adminId);

    Admin getAdminById(Long adminId);

    List<Admin> getAllAdmins();
}
