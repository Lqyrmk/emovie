package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.User;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:47
 */
public interface UserService extends IService<User> {

    /**
     * @description: 根据用户名查询用户
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:02
     * @param: [username]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    User login(String username, String password);

    /**
     * @description:
     * @author: YuanmingLiu
     * @date: 2023/3/19 0:49
     * @param: [username]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    boolean existsName(String username);

    /**
     * @description:
     * @author: YuanmingLiu
     * @date: 2023/3/19 0:49
     * @param: [email]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    boolean existsEmail(String email);

    /**
     * @description: 根据手机号查询用户
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:56
     * @param: [phone]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    boolean existsPhone(String phone);

    /**
     * @description: 添加用户
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:33
     * @param: [user]
     * @return: void
     **/
    User registerUser(User user);


    void modifyPassword(String username, String newPassword);

    List<User> findAll();
}
