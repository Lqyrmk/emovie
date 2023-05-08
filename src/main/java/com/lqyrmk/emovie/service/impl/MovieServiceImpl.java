package com.lqyrmk.emovie.service.impl;

import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.mapper.MovieMapper;
import com.lqyrmk.emovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:21
 * @Description: MovieServiceImpl
 * @Version 1.0.0
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

//    @Override
//    public boolean existsMovie(Movie movie) {
//        return false;
//    }

    @Override
    public Movie insertMovie(Movie movie) {
        movieMapper.addMovie(movie);
        return movie;
    }
}
