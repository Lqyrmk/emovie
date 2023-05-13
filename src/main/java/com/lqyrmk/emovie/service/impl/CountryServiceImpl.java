package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Language;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.mapper.CountryMapper;
import com.lqyrmk.emovie.mapper.MovieCountryMapper;
import com.lqyrmk.emovie.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 11:00
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private MovieCountryMapper movieCountryMapper;

    @Override
    public List<Country> getCountriesByKey(String countryKey, Integer limit) {

        // 模糊查询 按名称排序
        LambdaQueryWrapper<Country> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Country::getCountryName, countryKey).orderByAsc(Country::getCountryName);;

        // 分页，指定一次最多查出来的数量
        Page<Country> page = new Page<>(1, limit);
        countryMapper.selectPage(page, queryWrapper);
        List<Country> countries = page.getRecords();

        return countries;
    }

    @Override
    public List<Country> getMostUsedCountry(Integer limit) {

        // limit控制查询出来的记录个数
        Page<Map<String, Object>> page = new Page<>(1, limit);

        movieCountryMapper.getMostUsedCountry(page);

        List<Map<String, Object>> records = page.getRecords();

//        // 方案1 返回一个map 同LanguageServiceImpl

        // 方案2 返回一个countryList
        // 制作idList
        List<Long> idList = new ArrayList<>();
        for (Map<String, Object> record : records) {
            idList.add((Long) record.get("countryId"));
        }
        List<Country> countryList = countryMapper.selectBatchIds(idList);

        return countryList;
    }
}
