package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.MovieGenre;
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


    @Override
    public void addMovieToGenre(MovieGenre movieGenre) {
        movieGenreMapper.addMovieToGenre(movieGenre);
    }
}
