package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.MovieGenre;
import com.lqyrmk.emovie.service.GenreService;
import com.lqyrmk.emovie.service.MovieGenreService;
import com.lqyrmk.emovie.service.MovieService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/9 22:31
 */
@Api(tags = "电影接口")
@Slf4j
@RestController
@RequestMapping("/movieGenre")
public class MovieGenreController {

    @Autowired
    private MovieGenreService movieGenreService;

    @PostMapping
    @PreAuthorize("hasAuthority('system:manage')")
    @ApiOperation(value = "添加电影到类目中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "movieGenre", value = "电影类目映射信息", required = true)
    })
    public Result<Map<String, Object>> addMovieToGenres(@RequestBody MovieGenre movieGenre) {
        // 添加电影到类目中
        movieGenreService.addMovieToGenre(movieGenre);

        return Result.success(null, "添加成功！");

    }


}
