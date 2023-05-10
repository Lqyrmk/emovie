package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.MovieCountry;
import com.lqyrmk.emovie.entity.MovieGenre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/9 22:37
 */
@Mapper
public interface MovieGenreMapper extends BaseMapper<MovieGenre> {

    /**
     * @description: 分步查询所有电影和电影制片国家信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/10 20:20
     * @param: [movieId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.MovieGenre>
     **/
    List<MovieGenre> getAllMovieAndCountryByStep2(@Param("movieId") Long movieId);

    /**
     * @description: 添加电影到类目中
     * @author: YuanmingLiu
     * @date: 2023/5/10 21:05
     * @param: [movieGenre]
     * @return: void
     **/
    void addMovieToGenre(MovieGenre movieGenre);
}
