package com.lqyrmk.emovie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    @ApiOperation("根据信息分页查询电影")
    @ApiImplicitParams({

    })
    public Result<List<Movie>> getMovieByPage(@RequestParam("current") Integer current,
                                              @RequestParam("size") Integer size,
                                              @RequestParam("movieNameKey") String movieNameKey,
                                              @RequestParam("countryName") String countryName,
                                              @RequestParam("genreName") String genreName) {
        List<Movie> movies = movieService.getMoviesByPage(current, size, movieNameKey, countryName, genreName);
        return Result.success(movies, "查询成功！");
    }

    /**
     * @description: 增加电影
     * @author: Limo
     * @date: 2023/4/1 16:50
     * @param: [com.lqyrmk.emovie.entity.Movie]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Movie>
     */
    @PostMapping
    @ApiOperation(value = "添加电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "movie", value = "电影信息", required = true)
    })
    public Result<Map<String, Object>> addMovie(@RequestBody Map<String, Object> movieMap) {

        // 获取制片国家id构成的list对象
//        List<Long> countryIdList = (List<Long>) movieMap.get("countryIdList");

        String countryIdListJsonStr = JSONObject.toJSONString(movieMap.get("countryIdList"));
        List<Long> countryIdList = JSONObject.parseArray(countryIdListJsonStr, Long.class);
//        System.out.println("countryIdList = " + countryIdList);
//        countryIdList.forEach(System.out::println);

        // 获取movie对象
        String movieJsonStr = JSONObject.toJSONString(movieMap.get("movie"));
        Movie movie = JSONObject.parseObject(movieJsonStr, Movie.class);

        System.out.println("movie = " + movie);
        System.out.println("countryIdList = " + countryIdList);

        // 添加电影信息
        movieService.save(movie);
        // 建立电影和国家的联系
        List<MovieCountry> movieCountryList = new ArrayList<>();
        for (Long countryId : countryIdList) {
            MovieCountry movieCountry = new MovieCountry();
            // 电影id
            movieCountry.setMovieId(movie.getMovieId());
            // 国家id
            movieCountry.setCountryId(countryId);
            movieCountryList.add(movieCountry);
        }
        movieCountryService.saveOrUpdateBatch(movieCountryList);

        Map<String, Object> map = new HashMap<>();
        map.put("countryIdList", countryIdList);
        map.put("movie", movie);

        return Result.success(map, "电影《" + movie.getTitle() + "》添加成功");
    }


}
