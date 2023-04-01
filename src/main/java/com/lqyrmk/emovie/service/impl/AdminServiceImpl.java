package com.lqyrmk.emovie.service.impl;

import com.lqyrmk.emovie.entity.Admin;
import com.lqyrmk.emovie.mapper.AdminMapper;
import com.lqyrmk.emovie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:20
 * @Description: AdminServiceImpl
 * @Version 1.0.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean existsAdminName(String name) {
        return (adminMapper.getAdminByName(name)!=null);
    }

    @Override
    public Admin insertAdmin(Admin admin) {
        adminMapper.addAdmin(admin);
        return admin;
    }
}
