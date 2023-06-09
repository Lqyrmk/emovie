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
     * @description: 分步查询所有电影完整信息的第一步
     * @author: YuanmingLiu
     * @date: 2023/5/10 14:49
     * @param: [countryKey]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Movie>
     **/
//    List<Movie> getAllMovieAndCountryByStep1(@Param("countryKey") String countryKey);
    Page<Movie> getAllMovieByStep1(@Param("page") Page<Movie> page,
                                   @Param("movieNameKey") String movieNameKey,
                                   @Param("countryName") String countryName,
                                   @Param("genreName") String genreName,
                                   @Param("languageIso") String languageIso,
                                   @Param("year") String year,
                                   @Param("rating") String rating);

    /**
     * @description: 查询评分较高的电影
     * @author: YuanmingLiu
     * @date: 2023/5/17 0:06
     * @param: [page]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.lqyrmk.emovie.entity.Movie>
     **/
    Page<Movie> getPopularMovies(@Param("page") Page<Movie> page);

    /**
     * @description: 查询热度较高的电影
     * @author: YuanmingLiu
     * @date: 2023/5/17 0:06
     * @param: [page]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.lqyrmk.emovie.entity.Movie>
     **/
    Page<Movie> getHotMovies(@Param("page") Page<Movie> page);

    /**
     * @description: 根据电影id分步查询电影完整信息的第一步
     * @author: YuanmingLiu
     * @date: 2023/5/10 12:11
     * @param: [movieId]
     * @return: com.lqyrmk.emovie.entity.Movie
     **/
    Movie getMovieByIdByStep1(@Param("movieId") Long movieId);

    /**
     * @description: 查询推荐的电影
     * @author: YuanmingLiu
     * @date: 2023/6/8 20:02
     * @param: [page, ids]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.lqyrmk.emovie.entity.Movie>
     **/
    Page<Movie> getRecommendMovies(@Param("page") Page<Movie> page, @Param("movieIds") Long[] ids);

    /**
     * @description: 更新电影评分
     * @author: YuanmingLiu
     * @date: 2023/6/11 0:19
     * @param: [movieId]
     * @return: void
     **/
    void updateMovieRating(@Param("movieId") Long movieId);
}
