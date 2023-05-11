package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.MovieCountry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 12:06
 */
@Mapper
public interface MovieCountryMapper extends BaseMapper<MovieCountry> {

    /**
     * @description: 分步查询所有电影和电影制片国家信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/10 14:57
     * @param: [movieId, countryKey]
     * @return: java.util.List<com.lqyrmk.emovie.entity.MovieCountry>
     **/
    List<MovieCountry> getAllMovieAndCountryByStep2(@Param("movieId") Long movieId);

    /**
     * @description: 根据电影id分步查询电影和电影制片国家信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/10 12:22
     * @param: [movieId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.MovieCountry>
     **/
    List<MovieCountry> getMovieAndCountryByStep2(@Param("movieId") Long movieId,
                                                 @Param("countryKey") String countryKey);

    /**
     * @description: 反向测试第二步
     * @author: YuanmingLiu
     * @date: 2023/5/10 15:48
     * @param: [countryId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.MovieCountry>
     **/
    List<MovieCountry> getAllCountryAndMovieByStep2(@Param("countryId") String countryId);

}