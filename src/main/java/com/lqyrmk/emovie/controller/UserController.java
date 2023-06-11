package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.LoginUser;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:47
 */
@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @description: 根据id查询用户
     * @author: YuanmingLiu
     * @date: 2023/6/6 17:53
     * @param: []
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @GetMapping("/info")
    @ApiOperation(value = "根据id查询用户")
    @ApiImplicitParams({
    })
    public Result<User> getUserById() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserId, userId)
                .select(User::getUserId, User::getUsername, User::getEmail, User::getPhone, User::getGender);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return Result.success(user, "用户信息查询成功！");
        }
        return Result.error("用户信息查询失败！");

    }

//    @GetMapping
//    @ApiOperation(value = "查询所有用户接口")
//    @ApiImplicitParams({
//    })
//    public Result<List<User>> getAllUsers() {
//        return Result.success(userService.list(null));
//    }

    /**
     * @description: 用户登录
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:12
     * @param: [session, user]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户登录信息", required = true)
    })
    public Result<Map<String, Object>> login(@RequestBody User user) {
        // 登录
        return userService.login(user);
    }

    /**
     * @description: 用户退出登录
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:14
     * @param: [session]
     * @return: com.lqyrmk.emovie.common.Result<java.lang.String>
     **/
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录")
    @ApiImplicitParams({
    })
    public Result<String> logout() {
        return userService.logout();
    }

    /**
     * @description: 用户注册
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:41
     * @param: [user]
     * @return: void
     **/
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户注册信息", required = true)
    })
    public Result<User> register(@RequestBody User user) {
        userService.register(user);
        return Result.success(null, "注册成功！");
    }


    /**
     * @description: 修改密码
     * @author: YuanmingLiu
     * @date: 2023/3/19 23:56
     * @param: [user, newPassword]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @PutMapping("/modifyPwd")
    @ApiOperation(value = "用户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userMap", value = "需要修改密码的用户信息", required = true)
    })
    public Result<Map<String, Object>> modifyPassword(@RequestBody Map<String, Object> userMap) {
        // 用户名
        String username = (String) userMap.get("username");
        // 原密码
        String oldPassword = DigestUtils.md5DigestAsHex(((String) userMap.get("password")).getBytes());
        // 新密码
        String newPassword = DigestUtils.md5DigestAsHex(((String) userMap.get("newPassword")).getBytes());

        // 根据用户名和密码查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username).eq(User::getPassword, oldPassword);

        // 判断输入的用户名或密码是否正确
        if (userService.getOne(queryWrapper) == null) {
            return Result.error("用户名或密码错误");
        }

        // 修改密码
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUsername, username).eq(User::getPassword, oldPassword)
                .set(User::getPassword, newPassword);
        userService.update(updateWrapper);
        return Result.success(null, "密码修改成功！");
    }


}
