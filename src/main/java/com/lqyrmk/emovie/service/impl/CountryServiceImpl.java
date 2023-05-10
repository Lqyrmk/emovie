package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.mapper.CountryMapper;
import com.lqyrmk.emovie.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 11:00
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> getCountriesByKey(String countryKey) {
        // 模糊查询
        LambdaQueryWrapper<Country> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(Country::getCountryName, countryKey);
        List<Country> countries = countryMapper.selectList(queryWrapper);
        return countries;
    }
}
