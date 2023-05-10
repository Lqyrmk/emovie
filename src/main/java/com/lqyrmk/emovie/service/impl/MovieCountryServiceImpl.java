package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.MovieCountry;
import com.lqyrmk.emovie.mapper.MovieCountryMapper;
import com.lqyrmk.emovie.service.MovieCountryService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 22:25
 */
@Service
public class MovieCountryServiceImpl extends ServiceImpl<MovieCountryMapper, MovieCountry> implements MovieCountryService {
}
