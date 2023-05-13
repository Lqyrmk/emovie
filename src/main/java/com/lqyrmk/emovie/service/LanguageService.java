package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Language;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/13 0:12
 */
public interface LanguageService extends IService<Language> {

    /**
     * @description: 根据关键词查询电影语言
     * @author: YuanmingLiu
     * @date: 2023/5/13 0:19
     * @param: [languageKey]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Language>
     **/
    List<Language> getLanguageByKey(String languageKey, Integer limit);

    /**
     * @description: 查询最多电影使用的语言
     * @author: YuanmingLiu
     * @date: 2023/5/13 17:22
     * @param: [limit]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Language>
     **/
    List<Language> getMostUsedLanguage(Integer limit);
}
