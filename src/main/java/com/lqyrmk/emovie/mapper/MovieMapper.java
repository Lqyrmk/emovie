package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqyrmk.emovie.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:23
 * @Description: MovieMapper
 * @Version 1.0.0
 */

@Mapper
public interface MovieMapper extends BaseMapper<Movie> {

    void addMovie(Movie movie);

    /**
     * @description: 分步查询所有电影和电影制片国家信息的第一步
     * @author: YuanmingLiu
     * @date: 2023/5/10 14:49
     * @param: [countryKey]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Movie>
     **/
//    List<Movie> getAllMovieAndCountryByStep1(@Param("countryKey") String countryKey);
    Page<Movie> getAllMovieAndCountryByStep1(@Param("page") Page<Movie> page,
                                             @Param("movieNameKey") String movieNameKey,
                                             @Param("countryName") String countryName,
                                             @Param("genreName") String genreName);

    /**
     * @description: 根据电影id分步查询电影和电影制片国家信息的第一步
     * @author: YuanmingLiu
     * @date: 2023/5/10 12:11
     * @param: [movieId]
     * @return: com.lqyrmk.emovie.entity.Movie
     **/
    Movie getMovieAndCountryByStep1(@Param("movieId") Long movieId,
                                    @Param("countryKey") String countryKey);

    /**
     * @description: 反向测试第三步
     * @author: YuanmingLiu
     * @date: 2023/5/10 15:43
     * @param: [countryKey]
     * @return: com.lqyrmk.emovie.entity.Movie
     **/
    Movie getAllCountryAndMovieByStep3(@Param("movieId") Long movieId);
}
