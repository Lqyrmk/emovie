package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.mapper.UserMapper;
import com.lqyrmk.emovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.getUserByNameAndPassword(username, password);
    }

    @Override
    public boolean existsName(String username) {
        return (userMapper.getUserByName(username) != null);
    }

    @Override
    public boolean existsEmail(String email) {
        return (userMapper.getUserByEmail(email) != null);
    }

    @Override
    public boolean existsPhone(String phone) {
        return (userMapper.getUserByPhone(phone) != null);
    }

    @Override
    public User registerUser(User user) {
        userMapper.addUser(user);
        return user;
    }

    @Override
    public void modifyPassword(String username, String newPassword) {
        userMapper.updatePassword(username, newPassword);
    }

    @Override
    public List<User> findAll() {
        return userMapper.getAll();
    }

}
