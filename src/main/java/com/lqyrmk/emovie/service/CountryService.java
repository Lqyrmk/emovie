package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Country;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 11:00
 */
public interface CountryService extends IService<Country> {

    /**
     * @description: 根据关键词查询制片国家
     * @author: YuanmingLiu
     * @date: 2023/5/10 11:09
     * @param: [countryKey]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Country>
     **/
    List<Country> getCountriesByKey(String countryKey, Integer limit);

    /**
     * @description: 查询最多电影涉及的制片国家
     * @author: YuanmingLiu
     * @date: 2023/5/13 23:45
     * @param: [limit]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Country>
     **/
    List<Country> getMostUsedCountry(Integer limit);
}
