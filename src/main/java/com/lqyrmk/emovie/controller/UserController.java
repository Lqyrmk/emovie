package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.service.RatingsService;
import com.lqyrmk.emovie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping
    @ApiOperation(value = "查询所有用户接口")
    @ApiImplicitParams({
    })
    public Result<List<User>> getAllUsers() {
        return Result.success(userService.list(null));
    }

    /**
     * @description: 用户登录
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:12
     * @param: [session, user]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户登录信息", required = true)
    })
    public Result<User> login(HttpSession session, @RequestBody User user) {
        // 获取输入的用户名和密码
        String username = user.getUsername();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        // 根据用户名和密码查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username).eq(User::getPassword, password);

        User actualUser = userService.getOne(queryWrapper);

        // 判断是否存在该用户
        if (actualUser == null) {
            return Result.error("用户名或密码错误");
        }

        // 登录成功，将用户id存入session中
        session.setAttribute("loginUser", actualUser.getUserId());
        return Result.success(actualUser, "登录成功！");
    }

    /**
     * @description: 用户退出登录
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:14
     * @param: [session]
     * @return: com.lqyrmk.emovie.common.Result<java.lang.String>
     **/
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录接口")
    public Result<String> logout(HttpSession session) {
        // 清空session中的登录用户
        session.removeAttribute("loginUser");
        return Result.success(null, "已退出登录");
    }

    /**
     * @description: 用户注册
     * @author: YuanmingLiu
     * @date: 2023/3/18 17:41
     * @param: [user]
     * @return: void
     **/
    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户注册信息", required = true)
    })
    public Result<User> register(@RequestBody User user) {
        // md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        // 查询是否存在相同的用户名
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        if (userService.getOne(queryWrapper) != null) {
            return Result.error("用户名已存在");
        }
        // 查询邮箱是否已被绑定
        LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(User::getEmail, user.getEmail());
        if (userService.getOne(queryWrapper2) != null) {
            return Result.error("该邮箱已绑定其他账号");
        }
        // 查询手机号是否已被绑定
        LambdaQueryWrapper<User> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(User::getPhone, user.getPhone());
        if (userService.getOne(queryWrapper3) != null) {
            return Result.error("该手机号已绑定其他账号");
        }
        // 用户可被注册
        userService.save(user);
        return Result.success(user, "注册成功！");
    }


    /**
     * @description: 用户修改密码接口
     * @author: YuanmingLiu
     * @date: 2023/3/19 23:56
     * @param: [user, newPassword]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @PutMapping("/modifyPwd")
    @ApiOperation(value = "用户修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "需要修改密码的用户信息", required = true)
    })
    public Result<Map<String, Object>> modifyPassword(@RequestBody Map<String, Object> userMap) {
        // 用户名
        String username= (String) userMap.get("username");
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
