package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Admin;
import com.lqyrmk.emovie.entity.Genres;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.service.AdminService;
import com.lqyrmk.emovie.service.GenresService;
import com.lqyrmk.emovie.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 15:33
 * @Description: AdminController
 * @Version 1.0.0
 */

@Api(tags = "管理员接口")
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * @description: 管理员登录
     * @author: YuanmingLiu
     * @date: 2023/5/8 20:50
     * @param: [session, admin]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.User>
     **/
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "admin", value = "管理员登录信息", required = true)
    })
    public Result<Admin> login(HttpSession session, @RequestBody Admin admin) {
        // 获取输入的管理员账号和密码
        String adminName = admin.getName();
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());

        // 根据管理员账号和密码查询管理员信息
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName, adminName).eq(Admin::getPassword, password);

        Admin actualAdmin = adminService.getOne(queryWrapper);

        // 判断是否存在该管理员
        if (actualAdmin == null) {
            return Result.error("管理员账号或密码错误");
        }

        // 登录成功，将管理员id存入session中
        session.setAttribute("loginAdmin", actualAdmin.getAdminId());
        return Result.success(actualAdmin, "登录成功！");
    }


    @PostMapping
    @ApiOperation(value = "新增管理员接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "admin", value = "管理员信息", required = true)
    })
    public Result<Admin> addAdmin(@RequestBody Admin admin) {
        // md5加密
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        // 查询是否存在相同的管理员账号
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName, admin.getName());
        // 管理员账号已存在
        if (adminService.getOne(queryWrapper) != null) {
            return Result.error("管理员账号已存在");
        }
        // 不存在时可以新增管理员
        adminService.save(admin);
        return Result.success(admin, "管理员 " + admin.getName() + " 添加成功！");
    }

}
