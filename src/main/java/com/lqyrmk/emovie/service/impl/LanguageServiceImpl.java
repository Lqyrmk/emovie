package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Language;
import com.lqyrmk.emovie.entity.MovieLanguage;
import com.lqyrmk.emovie.mapper.LanguageMapper;
import com.lqyrmk.emovie.mapper.MovieLanguageMapper;
import com.lqyrmk.emovie.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/13 0:12
 */
@Service
public class LanguageServiceImpl extends ServiceImpl<LanguageMapper, Language> implements LanguageService {

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private MovieLanguageMapper movieLanguageMapper;

    @Override
    public List<Language> getLanguageByKey(String languageKey) {

        // 模糊查询 按名称排序
        // iso_639_1和语言名称都进行匹配
        LambdaQueryWrapper<Language> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Language::getLanguageName, languageKey)
                .or()
                .like(Language::getIso, languageKey)
                .orderByAsc(Language::getLanguageName);;

        // 分页，一次最多查出来8个
        Page<Language> page = new Page<>(1, 8);
        languageMapper.selectPage(page, queryWrapper);
        List<Language> languageList = page.getRecords();

        return languageList;
    }

    @Override
    public List<Language> getMostUsedLanguage(Integer limit) {

        // limit控制查询出来的记录个数
        Page<Map<String, Object>> page = new Page<>(1, limit);

        movieLanguageMapper.getMostUsedLanguage(page);

        List<Map<String, Object>> records = page.getRecords();

//        // 方案1 返回一个map
//        for (Map<String, Object> record : records) {
//            Language language = languageMapper.selectById((Long) record.get("languageId"));
//            record.put("languageName", language.getLanguageName());
//        }

        // 方案2 返回一个languageList
        // 制作idList
        List<Long> idList = new ArrayList<>();
        for (Map<String, Object> record : records) {
            idList.add((Long) record.get("languageId"));
        }
        List<Language> languageList = languageMapper.selectBatchIds(idList);

        return languageList;
    }
}
