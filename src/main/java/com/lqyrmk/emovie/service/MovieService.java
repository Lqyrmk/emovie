package com.lqyrmk.emovie.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Movie;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 15:46
 * @Description: MovieService
 * @Version 1.0.0
 */
public interface MovieService extends IService<Movie> {

    /**
     * @description: 增加电影
     * @author: Limo
     * @date: 2023/4/1 16:22
     * @param: [com.lqyrmk.emovie.entity.Movie]
     * @return: com.lqyrmk.emovie.entity.Movie
     */
    Movie insertMovie(Movie movie);
}
