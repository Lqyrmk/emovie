package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.common.MovieException;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Movie;
import com.lqyrmk.emovie.entity.Person;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.entity.User;
import com.lqyrmk.emovie.mapper.MovieMapper;
import com.lqyrmk.emovie.mapper.RatingsMapper;
import com.lqyrmk.emovie.mapper.UserMapper;
import com.lqyrmk.emovie.service.RatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        LambdaQueryWrapper<Ratings> ratingsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ratingsLambdaQueryWrapper.eq(Ratings::getUserId, ratings.getUserId())
                .eq(Ratings::getMovieId, ratings.getMovieId())
                .select(Ratings::getRating);

        if (ratingsMapper.selectOne(ratingsLambdaQueryWrapper) == null) {
            // 未参与过评分，添加评分记录
            int record = ratingsMapper.insert(ratings);
            log.info("***评分为{}...", ratings.getRating());
            return record;
        }

        // 参与过评分，更新评分记录
//        throw new MovieException("用户已对该电影评过分，评分失败！");
        Ratings updateRating = new Ratings();
        updateRating.setRating(ratings.getRating());
        int record = ratingsMapper.update(updateRating, ratingsLambdaQueryWrapper);
        log.info("***评分修改为{}...", ratings.getRating());
        return record;
    }

    @Override
    public Map<String, Object> getRatingsByUserId(Long userId) {

        LambdaQueryWrapper<Ratings> ratingsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ratingsLambdaQueryWrapper.eq(Ratings::getUserId, userId)
                .select(Ratings::getUserId, Ratings::getMovieId, Ratings::getRating);
        List<Ratings> ratingsList = ratingsMapper.selectList(ratingsLambdaQueryWrapper);

        if (ratingsList.size() == 0) {
            throw new MovieException("暂未对电影评分");
        }

        int ratingSum = 0;
        Map<Long, Ratings> infoMap = new HashMap<>();

        for (Ratings rating : ratingsList) {
            // 计算评分分数总合
            ratingSum += rating.getRating();

            // 查询列表中的电影并存储起来
            LambdaQueryWrapper<Movie> movieLambdaQueryWrapper = new LambdaQueryWrapper<>();
            movieLambdaQueryWrapper.eq(Movie::getMovieId, rating.getMovieId())
                    .select(Movie::getMovieId, Movie::getTitle, Movie::getOriginalTitle, Movie::getRatingAverage, Movie::getReleaseDate);
            Movie movie = movieMapper.selectOne(movieLambdaQueryWrapper);
            rating.setMovie(movie);
            infoMap.put(rating.getMovieId(), rating);
        }

        // 计算平均评分
        double ratingAverage = ratingSum / ratingsList.size();

        Map<String, Object> map = new HashMap<>();
        map.put("ratingAverage", ratingAverage);
        map.put("infoMap", infoMap);

        return map;

    }
}
