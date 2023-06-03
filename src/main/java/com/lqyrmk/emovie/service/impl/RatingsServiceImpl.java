package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.common.MovieException;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.mapper.MovieMapper;
import com.lqyrmk.emovie.mapper.RatingsMapper;
import com.lqyrmk.emovie.mapper.UserMapper;
import com.lqyrmk.emovie.service.RatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 12:04
 * @Description: RatingsServiceImpl
 * @Version 1.0.0
 */
@Slf4j
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
    public int addRatings(Ratings ratings) {

        // 判断评分的用户是否存在
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId).eq(User::getUserId, ratings.getUserId());
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if (user == null) {
            throw new MovieException("用户不存在");
        }

        // 判断被评分的电影是否存在
        LambdaQueryWrapper<Movie> movieLambdaQueryWrapper = new LambdaQueryWrapper<>();
        movieLambdaQueryWrapper.select(Movie::getMovieId).eq(Movie::getMovieId, ratings.getMovieId());
        Movie movie = movieMapper.selectOne(movieLambdaQueryWrapper);
        if (movie == null) {
            throw new MovieException("电影不存在");
        }

        // 判断用户是否已对该电影评过分
        LambdaUpdateWrapper<Ratings> ratingsLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ratingsLambdaUpdateWrapper.eq(Ratings::getUserId, ratings.getUserId())
                .eq(Ratings::getMovieId, ratings.getMovieId());

        if (ratingsMapper.selectOne(ratingsLambdaUpdateWrapper) == null) {
            // 未参与过评分，添加评分记录
            int record = ratingsMapper.insert(ratings);
            log.info("***评分为{}...", ratings.getRating());
            return record;
        }

        // 参与过评分，更新评分记录
//        throw new MovieException("用户已对该电影评过分，评分失败！");
        ratingsLambdaUpdateWrapper.set(Ratings::getRating, ratings.getRating());
        int record = ratingsMapper.update(null, ratingsLambdaUpdateWrapper);
        log.info("***评分修改为{}...", ratings.getRating());
        return record;
    }
}
