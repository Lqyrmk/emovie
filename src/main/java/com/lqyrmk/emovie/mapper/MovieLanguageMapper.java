package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqyrmk.emovie.entity.MovieLanguage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
