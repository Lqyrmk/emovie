package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.entity.Language;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/13 0:12
 */
@Mapper
public interface LanguageMapper extends BaseMapper<Language> {

    /**
     * @description: 分步查询所有电影完整信息的第三步
     * @author: YuanmingLiu
     * @date: 2023/5/13 18:27
     * @param: [languageId]
     * @return: com.lqyrmk.emovie.entity.Language
     **/
    Language getAllMovieByStep3(@Param("languageId") Long languageId);

}
