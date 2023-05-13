package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

//    /**
//     * @description: 根据关键词查询电影语言
//     * @author: YuanmingLiu
//     * @date: 2023/5/13 0:21
//     * @param: [page, languageKey]
//     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.lqyrmk.emovie.entity.Language>
//     **/
//    Page<Language> getLanguageByKey(@Param("page") Page<Language> page,
//                                    @Param("languageKey") String languageKey);
}
