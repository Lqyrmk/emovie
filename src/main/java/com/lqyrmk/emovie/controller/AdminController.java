package com.lqyrmk.emovie.controller;

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
    private MovieService movieService;
    private GenresService genresService;
    private AdminService adminService;

    public AdminController() {
    }

    /**
     * @description: 管理员登录(暂未实现)
     * @author: Limo
     * @date: 2023/4/1 16:42
     * @param: [javax.servlet.http.HttpSession, Admin]
     * @return: com.lqyrmk.emovie.common.Result<Admin>
     */
//    @PostMapping("/login")
//    @ApiOperation(value = "管理员登录接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "admin", value = "管理员登录信息", required = true)
//    })
//    public Result<Admin> login(HttpSession session, @RequestBody Admin admin){
//        return Result.success(admin);
//    }


    @PostMapping("/addadmin")
    @ApiOperation(value = "新增管理员接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "admin", value = "管理员信息", required = true)
    })
    public Result<Admin> addAdmin(@RequestBody Admin admin) {
        // 查询是否存在相同的用户名
        String name =admin.getName();
        if (adminService.existsAdminName(name)) {
            return Result.error("用户名已存在");
        }

        // 新增管理员
        adminService.insertAdmin(admin);
        return Result.success(admin);
    }


    /**
     * @description: 增加电影
     * @author: Limo
     * @date: 2023/4/1 16:50
     * @param: [com.lqyrmk.emovie.entity.Movie]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Movie>
     */
    @PostMapping("/addmovie")
    @ApiOperation(value = "增加电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "movie", value = "电影信息", required = true)
    })
    public Result<Movie> addMovie(@RequestBody Movie movie){
        movieService.insertMovie(movie);
        return Result.success(movie);
    }


    /**
     * @description: 增加电影类目
     * @author: Limo
     * @date: 2023/4/1 17:07
     * @param: [com.lqyrmk.emovie.entity.Genres]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Genres>
     */
    @PostMapping("/addgenre")
    @ApiOperation(value = "增加电影类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "genre", value = "类目信息", required = true)
    })
    public Result<Genres> addGenre(@RequestBody Genres genres){
        genresService.insertGenre(genres);
        return Result.success(genres);
    }
}
