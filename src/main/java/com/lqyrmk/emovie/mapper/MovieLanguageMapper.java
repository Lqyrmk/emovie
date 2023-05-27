package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqyrmk.emovie.entity.MovieLanguage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/13 17:37
 */
@Mapper
public interface MovieLanguageMapper extends BaseMapper<MovieLanguage> {

    /**
     * @description: 查询最多电影使用的语言
     * @author: YuanmingLiu
     * @date: 2023/5/13 17:41
     * @param: [page]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.lqyrmk.emovie.entity.MovieLanguage>
     **/
    Page<Map<String, Object>> getMostUsedLanguage(@Param("page") Page<Map<String, Object>> page);

    /**
     * @description: 分步查询所有电影完整信息的第二步
     * @author: YuanmingLiu
     * @date: 2023/5/13 18:24
     * @param: [movieId]
     * @return: java.util.List<com.lqyrmk.emovie.entity.MovieLanguage>
     **/
    List<MovieLanguage> getAllMovieByStep2(@Param("movieId") Long movieId);


}
