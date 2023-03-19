package com.lqyrmk.emovie.controller;

import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
        String password = user.getPassword();

        // 根据用户名和密码查询用户信息
        User actualUser = userService.login(username, password);

        // 判断是否存在该用户
        if (actualUser == null) {
            return Result.error("用户名或密码错误");
        }

        // 登录成功，将用户id存入session中
        session.setAttribute("loginUser", user.getUserId());
        return Result.success(actualUser);
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
        return Result.success("已退出登录");
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
        // 查询是否存在相同的用户名
        String username = user.getUsername();
        if (userService.existsName(username)) {
            return Result.error("用户名已存在");
        }
        // 查询邮箱是否已被绑定
        String email = user.getEmail();
        if (userService.existsEmail(email)) {
            return Result.error("该邮箱已绑定其他账号");
        }
        // 查询手机号是否已被绑定
        String phone = user.getPhone();
        if (userService.existsPhone(phone)) {
            return Result.error("该手机号已绑定其他账号");
        }
        // 用户可被注册
        userService.registerUser(user);
        return Result.success(user);
    }


    @PutMapping("/modify/password")
    @ApiOperation(value = "用户修改密码接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "user", value = "需要修改密码的用户信息", required = true)
//    })
    public Result<User> modifyPassword(@RequestBody User user, @RequestParam("newPassword") String newPassword) {
        // 获取输入的用户名和密码
        String username = user.getUsername();
        String password = user.getPassword();

        // 根据用户名和密码查询用户信息
        User actualUser = userService.login(username, password);

        // 判断输入的密码是否正确
        if (actualUser == null) {
            return Result.error("密码错误");
        }
        // 用户修改密码成功
        userService.modifyPassword(username, newPassword);
        return Result.success(user);
    }
}
