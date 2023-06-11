package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.LoginUser;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.mapper.RatingsMapper;
import com.lqyrmk.emovie.service.MovieService;
import com.lqyrmk.emovie.service.RatingsService;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 评分接口
 * @Author YuanmingLiu
 * @Date 2023/5/8 19:25
 */
@Api(tags = "评分接口")
@Slf4j
@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    /**
     * @description: 查询个人评分信息
     * @author: YuanmingLiu
     * @date: 2023/5/11 19:57
     * @param: [userId]
     * @return: com.lqyrmk.emovie.common.Result<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    @GetMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "查询个人评分信息")
    @ApiImplicitParams({
    })
    public Result<Map<String, Object>> getRatingsByUserId() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        Map<String, Object> ratingsMap = ratingsService.getRatingsByUserId(userId);

        return Result.success(ratingsMap, "查询成功");
    }

    /**
     * @description: 查询用户对某电影的评分
     * @author: YuanmingLiu
     * @date: 2023/6/6 21:49
     * @param: [userId, movieId]
     * @return: com.lqyrmk.emovie.common.Result<java.lang.Integer>
     **/
    @GetMapping("/{movieId}")
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "查询用户对某电影的评分")
    @ApiImplicitParams({
    })
    public Result<Ratings> getRatingsByUserId(@PathVariable("movieId") Long movieId) {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        LambdaQueryWrapper<Ratings> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ratings::getUserId, userId)
                .eq(Ratings::getMovieId, movieId)
                .select(Ratings::getId, Ratings::getUserId, Ratings::getMovieId, Ratings::getRating);
        Ratings ratings = ratingsService.getOne(queryWrapper);
        if (ratings != null) {
            return Result.success(ratings, "查询成功");
        }
        return Result.error("查询失败");
    }

    /**
     * @description: 用户评分
     * @author: Limo
     * @date: 2023/4/1 15:39
     * @param: [com.lqyrmk.emovie.entity.Ratings]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Ratings>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation(value = "用户评分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ratings", value = "用户评分信息", required = true)
    })
    public Result<Map<String, Object>> addRatings(@RequestBody Ratings ratings) {
        int record = ratingsService.addRatings(ratings);
        if (record > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("ratings", ratings);
            map.put("record", record);
            return Result.success(map, "评分成功！");
        }
        return Result.error("评分失败！");
    }


}
