package com.lqyrmk.emovie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.MovieCountry;
import com.lqyrmk.emovie.service.MovieCountryService;
import com.lqyrmk.emovie.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/8 19:26
 */
@Api(tags = "电影接口")
@Slf4j
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieCountryService movieCountryService;

//    @GetMapping
//    @ApiOperation("查询所有电影")
//    @ApiImplicitParams({
//
//    })
//    public Result<Country> getAllMovies() {
////    public Result<List<Movie>> getAllMovies() {
////        List<Movie> list = movieService.getAllMovies();
//        Country list = movieService.getAllMovies();
//        return Result.success(list, "查询成功！");
//    }

    @GetMapping("/{movieId}")
    @ApiOperation("根据id查询电影")
    @ApiImplicitParams({
    })
    public Result<Movie> getMovieById(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return Result.success(movie, "查询成功！");
    }

    @GetMapping
    @ApiOperation("根据电影名模糊查询电影")
    @ApiImplicitParams({
    })
    public Result<List<Movie>> getMovieByNameKey(@RequestParam("movieNameKey") String movieNameKey,
                                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<Movie> movies = movieService.getMovieByNameKey(movieNameKey, limit);
        return Result.success(movies, "查询成功！");
    }


    @GetMapping("/page")
    @ApiOperation("根据信息分页查询电影")
    @ApiImplicitParams({

    })
    public Result<Page<Movie>> getMovieByPage(@RequestParam("current") Integer current,
                                              @RequestParam("size") Integer size,
                                              @RequestParam(value = "movieNameKey", required = false) String movieNameKey,
                                              @RequestParam(value = "countryName", required = false) String countryName,
                                              @RequestParam(value = "genreName", required = false) String genreName,
                                              @RequestParam(value = "languageIso", required = false) String languageIso,
                                              @RequestParam(value = "year", required = false) String year,
                                              @RequestParam(value = "rating", required = false) String rating,
                                              @RequestParam(value = "status", defaultValue = "1") String status) {


        log.info("******{}", status);

        // 根据参数的不同查询不同的结果
        if (movieNameKey != null && countryName != null && genreName != null
                && languageIso != null && year != null && rating != null) {
            // 执行筛选
            log.info("***执行筛选***");
//            Map<String, Object> moviesMap =
            Page<Movie> moviesMap =
                    movieService.getMoviesByPage(current, size, movieNameKey, countryName,
                            genreName, languageIso, year, rating);
            if (moviesMap != null) {
                return Result.success(moviesMap, "筛选成功！");
            }
            return Result.error("查询失败，请重试！");
        } else if ("1".equals(status)) {
            // 获取高分电影
            log.info("***获取高分电影***");
            Page<Movie> popularMovies = movieService.getPopularMovies(current, size);
            if (popularMovies != null) {
                return Result.success(popularMovies, "查询高分电影成功");
            }
            return Result.error("查询失败，请重试！");
        } else if ("2".equals(status)) {
            // 获取热门电影
            log.info("***获取热门电影***");
            Page<Movie> hotMovies = movieService.getHotMovies(current, size);
            if (hotMovies != null) {
                return Result.success(hotMovies, "查询热门电影成功");
            }
            return Result.error("查询失败，请重试！");
        }
        // 获取热门电影
        log.info("***获取推荐电影***");
        Long userId = 1L;
        Page<Movie> recommendMovies = movieService.getRecommendMovies(userId, current, size);
        if (recommendMovies != null) {
            return Result.success(recommendMovies, "查询推荐电影成功");
        }
        return Result.error("查询失败，请重试！");

    }

    /**
     * @description: 增加电影
     * @author: Limo
     * @date: 2023/4/1 16:50
     * @param: [com.lqyrmk.emovie.entity.Movie]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Movie>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('system:manage')")
    @ApiOperation(value = "添加电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "movieMap", value = "电影信息", required = true)
    })
    public Result<Map<String, Object>> addMovie(@RequestBody Map<String, Object> movieMap) {

        Map<String, Object> resMap = movieService.insertMovie(movieMap);

        return Result.success(resMap, "电影添加成功");

    }


}
