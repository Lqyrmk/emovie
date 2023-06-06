package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lqyrmk.emovie.common.Result;
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
     * @return: com.lqyrmk.emovie.common.Result<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/{userId}")
    @ApiOperation(value = "查询个人评分信息接口")
    @ApiImplicitParams({
    })
    public Result<Map<String, Object>> getRatingsByUserId(@PathVariable Long userId) {

        Map<String, Object> ratingsMap = ratingsService.getRatingsByUserId(userId);

        return Result.success(ratingsMap, "查询成功");
    }


    /**
     * @description: 用户评分
     * @author: Limo
     * @date: 2023/4/1 15:39
     * @param: [com.lqyrmk.emovie.entity.Ratings]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Ratings>
     */
    @PostMapping
    @ApiOperation(value = "用户评分接口")
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

//    /**
//     * @description: 修改评分
//     * @author: YuanmingLiu
//     * @date: 2023/5/11 19:43
//     * @param: [ratings]
//     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Ratings>
//     **/
//    @PutMapping
//    @ApiOperation(value = "修改评分接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ratings", value = "用户评分信息", required = true)
//    })
//    public Result<Ratings> updateRatings(@RequestBody Ratings ratings) {
//
//        // 判断评分的用户是否存在
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.select(User::getUserId).eq(User::getUserId, ratings.getUserId());
//        if (userService.getOne(userLambdaQueryWrapper) != null) {
//            return Result.error("用户不存在");
//        }
//
//        // 判断被评分的电影是否存在
//        LambdaQueryWrapper<Movie> movieLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        movieLambdaQueryWrapper.select(Movie::getMovieId).eq(Movie::getMovieId, ratings.getMovieId());
//        if (movieService.getOne(movieLambdaQueryWrapper) != null) {
//            return Result.error("电影不存在");
//        }
//
//        // 修改评分记录
////        LambdaUpdateWrapper<Ratings> updateWrapper = new LambdaUpdateWrapper<>();
////        updateWrapper.eq(Ratings::getUserId, ratings.getUserId())
////                .eq(Ratings::getMovieId, ratings.getMovieId())
////                .set(Ratings::getRating, ratings.getRating());
////        ratingsService.update(updateWrapper);
//
//
//        ratingsService.updateById(ratings);
//
//        return Result.success(ratings, "已将对该电影的评分修改为：" + ratings.getRating());
//    }

    /* 放个SQL在这
        UPDATE movie,
        (
            SELECT movie_id, AVG(rating) as `rating_avg`
            FROM `ratings`
            GROUP BY movie_id
        ) r
        SET movie.rating_avg = r.rating_avg
        WHERE movie.movie_id = r.movie_id
     **/

}
