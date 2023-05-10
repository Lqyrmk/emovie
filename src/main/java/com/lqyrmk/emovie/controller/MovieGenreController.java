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
        movieLambdaQueryWrapper.select(Movie::getMovieId, Movie::getTitle);
        List<Map<String, Object>> movieList = movieService.listMaps(movieLambdaQueryWrapper);
        System.out.println("movieList = " + movieList);

        if (movieList.isEmpty()) {
            return Result.error("该电影不存在");
        }

        // 查询类目
        LambdaQueryWrapper<Genre> genreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        genreLambdaQueryWrapper.eq(Genre::getGenreName, genreName);
        Genre genre = genreService.getOne(genreLambdaQueryWrapper);

        if (genre == null) {
            return Result.error("该类目不存在");
        }

        // 查询该电影是否在类目中
        LambdaQueryWrapper<MovieGenre> movieGenreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        for (Map<String, Object> movie : movieList) {
            movieGenreLambdaQueryWrapper
                    .select(MovieGenre::getMovieId, MovieGenre::getGenreId)
                    .eq(MovieGenre::getMovieId, (Long) movie.get("movie_id"))
                    .eq(MovieGenre::getGenreId, genre.getGenreId());
            if (movieGenreService.getOne(movieGenreLambdaQueryWrapper) != null) {
                return Result.error("该电影已在所选类目中！");
            }
        }

        // 添加电影到类目中
        MovieGenre movieGenre = new MovieGenre();
        movieGenre.setMovieId((Long) movieList.get(0).get("movie_id"));
        movieGenre.setGenreId(genre.getGenreId());

        movieGenreService.save(movieGenre);
//        movieGenreService.addMovieToGenre(movieGenre);

        return Result.success(null, "添加成功！");

    }

}
