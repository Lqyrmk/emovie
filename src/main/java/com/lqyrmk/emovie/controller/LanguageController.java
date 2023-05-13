package com.lqyrmk.emovie.controller;

import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Language;
import com.lqyrmk.emovie.service.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description 电影语言接口
 * @Author YuanmingLiu
 * @Date 2023/5/13 0:11
 */
@Api(tags = "电影语言接口")
@Slf4j
@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    /**
     * @description: 根据关键词查询电影语言
     * @author: YuanmingLiu
     * @date: 2023/5/13 0:17
     * @param: [languageKey]
     * @return: com.lqyrmk.emovie.common.Result<java.util.List<com.lqyrmk.emovie.entity.Language>>
     **/
    @GetMapping
    @ApiOperation("根据关键词查询电影语言")
    @ApiImplicitParams({
    })
    public Result<List<Language>> getLanguageByKey(@RequestParam("languageKey") String languageKey) {
        List<Language> languageList = languageService.getLanguageByKey(languageKey);
        return Result.success(languageList, "查询成功！");
    }

    /**
     * @description: 查询最多电影使用的语言
     * @author: YuanmingLiu
     * @date: 2023/5/13 17:23
     * @param: [limit]
     * @return: com.lqyrmk.emovie.common.Result<java.util.List<com.lqyrmk.emovie.entity.Language>>
     **/
    @GetMapping("/most")
    @ApiOperation("查询最多电影使用的语言")
    @ApiImplicitParams({
    })
    public Result<List<Language>> getMostUsedLanguage(@RequestParam("limit") Integer limit) {
        List<Language> languageList = languageService.getMostUsedLanguage(limit);
        return Result.success(languageList, "查询成功");
    }



}
