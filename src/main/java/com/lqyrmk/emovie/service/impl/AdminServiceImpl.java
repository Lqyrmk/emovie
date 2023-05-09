package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

}
