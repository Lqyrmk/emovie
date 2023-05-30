package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Crew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/28 10:17
 */
@Mapper
public interface CrewMapper extends BaseMapper<Crew> {

    /**
     * @description: 分步查询所有电影完整信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/28 16:24
     * @param: [movieId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Crew>
     **/
    List<Crew> getAllMovieByStep2(@Param("movieId") Long movieId);

    /**
     * @description: 根据电影id分步查询电影完整信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/30 21:20
     * @param: [movieId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Crew>
     **/
    List<Crew> getMovieByIdByStep2(@Param("movieId") Long movieId);

}
