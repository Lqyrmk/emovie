package com.lqyrmk.emovie.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 15:46
 * @Description: MovieService
 * @Version 1.0.0
 */
public interface MovieService extends IService<Movie> {

    /**
     * @description: 查询所有电影
     * @author: YuanmingLiu
     * @date: 2023/5/10 12:51
     * @param: []
     * @return: java.util.List<com.lqyrmk.emovie.entity.Movie>
     **/
    Country getAllMovies();
//    List<Movie> getAllMovies();

    /**
     * @description: 根据信息分页查询电影
     * @author: YuanmingLiu
     * @date: 2023/5/10 19:09
     * @param: [current, size, movieNameKey, countryName, genreName]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Movie>
     **/
    Map<String, Object> getMoviesByPage(Integer current, Integer size, String movieNameKey, String countryName, String genreName);

    /**
     * @description: 根据id查询电影
     * @author: YuanmingLiu
     * @date: 2023/5/10 12:53
     * @param: [movieId]
     * @return: com.lqyrmk.emovie.entity.Movie
     **/
    Movie getMovieById(Long movieId);


    /**
     * @description: 根据电影名模糊查询电影
     * @author: YuanmingLiu
     * @date: 2023/5/11 22:27
     * @param: [movieNameKey]
     * @return: java.util.List<java.lang.String>
     **/
    List<String> getMovieByNameKey(String movieNameKey);


    /**
     * @description: 增加电影
     * @author: Limo
     * @date: 2023/4/1 16:22
     * @param: [com.lqyrmk.emovie.entity.Movie]
     * @return: com.lqyrmk.emovie.entity.Movie
     */
    Movie insertMovie(Movie movie);

}
