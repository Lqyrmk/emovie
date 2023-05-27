package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Cast;
import com.lqyrmk.emovie.entity.MovieLanguage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/25 16:43
 */
@Mapper
public interface CastMapper extends BaseMapper<Cast> {

    /**
     * @description: 分步查询所有电影完整信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/27 21:00
     * @param: [movieId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Cast>
     **/
    List<Cast> getAllMovieByStep2(@Param("movieId") Long movieId);

}
