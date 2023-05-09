package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:48
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * @description: 根据用户名和密码查询用户
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:03
     * @param: [username]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    @Select("select username, password, email, gender, phone from user where username=#{username} and password=#{password}")
    User getUserByNameAndPassword(@Param("username") String username,
                                  @Param("password") String password);

    /**
     * @description: 根据用户名查询用户
     * @author: YuanmingLiu
     * @date: 2023/3/19 0:20
     * @param: [username]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    @Select("select username, password, email, gender, phone from user where username=#{username}")
    User getUserByName(@Param("username") String username);

    /**
     * @description: 根据邮箱查询用户
     * @author: YuanmingLiu
     * @date: 2023/3/19 0:21
     * @param: [email]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    @Select("select username, password, email, gender, phone from user where email=#{email}")
    User getUserByEmail(@Param("email") String email);

    /**
     * @description: 根据手机号查询用户
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:58
     * @param: [phone]
     * @return: com.lqyrmk.emovie.entity.User
     **/
    @Select("select username, password, email, gender, phone from user where phone=#{phone}")
    User getUserByPhone(@Param("phone") String phone);

    /**
     * @description: 添加用户
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:36
     * @param: [user]
     * @return: void
     **/
//    @Insert("insert into user values (null, #{username}, #{password}, #{email}, #{gender}, #{phone})")
    void addUser(User user);

    @Update("update user set password=#{newPassword} where username=#{username}")
    void updatePassword(@Param("username")String username, @Param("newPassword")String newPassword);

    @Select("select * from user")
    List<User> getAll();

}
