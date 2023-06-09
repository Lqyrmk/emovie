package com.lqyrmk.emovie.controller;

import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Language;
import com.lqyrmk.emovie.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 10:59
 */
@Api(tags = "制片国家接口")
@Slf4j
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * @description: 查询所有制片国家
     * @author: YuanmingLiu
     * @date: 2023/5/10 11:19
     * @param: []
     * @return: com.lqyrmk.emovie.common.Result<java.util.List<com.lqyrmk.emovie.entity.Country>>
     **/
//    @GetMapping
//    @ApiOperation("查询所有制片国家")
//    @ApiImplicitParams({
//    })
//    public Result<List<Country>> getAllCountries() {
//        List<Country> countries = countryService.list();
//        System.out.println("countries = " + countries);
//        return Result.success(countries, "查询成功！");
//    }

    /**
     * @description: 根据关键词查询所有制片国家
     * @author: YuanmingLiu
     * @date: 2023/5/10 11:19
     * @param: [countryKey]
     * @return: com.lqyrmk.emovie.common.Result<java.util.List<com.lqyrmk.emovie.entity.Country>>
     **/
    @GetMapping
    @ApiOperation("根据关键词查询所有制片国家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countryKey", value = "国家搜索关键字", required = false),
            @ApiImplicitParam(name = "limit", value = "显示数量", required = false)
    })
    public Result<List<Country>> getCountriesByKey(@RequestParam(value = "countryKey", required = false) String countryKey,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        // 传了key，包括empty
        if (countryKey != null) {
            return Result.success(countryService.getCountriesByKey(countryKey, limit), "查询成功！");
        }
        // 未传key
        else {
            return Result.success(countryService.list(), "查询成功！");
        }
    }

    /**
     * @description: 查询最多电影涉及的制片国家
     * @author: YuanmingLiu
     * @date: 2023/5/13 23:45
     * @param: [limit]
     * @return: com.lqyrmk.emovie.common.Result<java.util.List<com.lqyrmk.emovie.entity.Country>>
     **/
    @GetMapping("/most")
    @ApiOperation("查询最多电影涉及的制片国家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "显示数量", required = false)
    })
    public Result<List<Country>> getMostUsedLanguage(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<Country> countryList = countryService.getMostUsedCountry(limit);
        return Result.success(countryList, "查询成功");
    }

}
