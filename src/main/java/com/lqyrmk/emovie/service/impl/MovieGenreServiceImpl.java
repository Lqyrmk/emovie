package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.common.MovieException;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.MovieGenre;
import com.lqyrmk.emovie.mapper.GenreMapper;
import com.lqyrmk.emovie.mapper.MovieGenreMapper;
import com.lqyrmk.emovie.mapper.MovieMapper;
import com.lqyrmk.emovie.service.MovieGenreService;
import com.lqyrmk.emovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/9 22:37
 */
@Service
public class MovieGenreServiceImpl extends ServiceImpl<MovieGenreMapper, MovieGenre> implements MovieGenreService {

    @Autowired
    private MovieGenreMapper movieGenreMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private GenreMapper genreMapper;


    @Override
    public void addMovieToGenre(MovieGenre movieGenre) {

        // 查询电影
        LambdaQueryWrapper<Movie> movieLambdaQueryWrapper = new LambdaQueryWrapper<>();
        movieLambdaQueryWrapper.eq(Movie::getMovieId, movieGenre.getMovieId());

        if (!movieMapper.exists(movieLambdaQueryWrapper)) {
            throw new MovieException("该电影不存在");
        }

        // 查询类目
        LambdaQueryWrapper<Genre> genreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        genreLambdaQueryWrapper.eq(Genre::getGenreId, movieGenre.getGenreId());

        if (!genreMapper.exists(genreLambdaQueryWrapper)) {
            throw new MovieException("该类目不存在");
        }

        // 查询该电影是否在类目中
        LambdaQueryWrapper<MovieGenre> movieGenreLambdaQueryWrapper = new LambdaQueryWrapper<>();
        movieGenreLambdaQueryWrapper
                .eq(MovieGenre::getMovieId, movieGenre.getMovieId())
                .eq(MovieGenre::getGenreId, movieGenre.getGenreId());
        if (movieGenreMapper.exists(movieGenreLambdaQueryWrapper)) {
            throw new MovieException("该电影已在所选类目中");
        }

        // 添加到类目中
        super.save(movieGenre);
    }
}
