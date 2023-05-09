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
import org.springframework.web.bind.annotation.*;

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
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieGenreService movieGenreService;

    @PostMapping
    @ApiOperation(value = "添加电影到类目中")
    public Result<Map<String, Object>> addMovieToGenres(@RequestParam("movieTitle") String movieTitle,
                                                        @RequestParam("genreName") String genreName) {
        // 查询电影
        LambdaQueryWrapper<Movie> movieLambdaQueryWrapper = new LambdaQueryWrapper<>();
        movieLambdaQueryWrapper.eq(Movie::getTitle, movieTitle);
        Movie movie = movieService.getOne(movieLambdaQueryWrapper);

        if (movie == null) {
            return Result.error("该电影不存在");
        }

        // 查询类目
        LambdaQueryWrapper<Genre> genreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        genreLambdaQueryWrapper.eq(Genre::getGenreName, genreName);
        Genre genre = genreService.getOne(genreLambdaQueryWrapper);

        if (genre == null) {
            return Result.error("该类目不存在");
        }

        // 添加电影到类目中
        MovieGenre movieGenre = new MovieGenre(movie.getMovieId(), genre.getGenreId());
        movieGenreService.save(movieGenre);

        return Result.success(null, "添加成功！");

    }

}
