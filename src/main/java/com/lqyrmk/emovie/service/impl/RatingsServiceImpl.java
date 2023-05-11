package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.mapper.MovieMapper;
import com.lqyrmk.emovie.mapper.RatingsMapper;
import com.lqyrmk.emovie.mapper.UserMapper;
import com.lqyrmk.emovie.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 12:04
 * @Description: RatingsServiceImpl
 * @Version 1.0.0
 */

@Service
public class RatingsServiceImpl extends ServiceImpl<RatingsMapper, Ratings> implements RatingsService {

    @Autowired
    private RatingsMapper ratingsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public boolean existsRating(Ratings ratings) {
        return (ratingsMapper.getRatingByUserIdAndMovieId(ratings.getUserId(), ratings.getMovieId()) != null);
    }

    @Override
    public Ratings insertRating(Ratings ratings) {
        ratingsMapper.addRating(ratings);
        return ratings;
    }

    @Override
    public void addRatings(Ratings ratings) {
        LambdaQueryWrapper<Ratings> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ratings::getUserId, ratings.getUserId())
                .eq(Ratings::getMovieId, ratings.getMovieId());

        // 判断是否存在评分
        if (ratingsMapper.selectOne(queryWrapper) != null) {
            // 存在评分则对原有评分进行修改
            ratingsMapper.updateById(ratings);
        }
        // 不存在评分则新增评分
        ratingsMapper.insert(ratings);
    }
}
