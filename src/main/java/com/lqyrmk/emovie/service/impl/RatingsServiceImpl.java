package com.lqyrmk.emovie.service.impl;

import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.mapper.RatingsMapper;
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
public class RatingsServiceImpl implements RatingsService {
    @Autowired
    private RatingsMapper ratingsMapper;

    @Override
    public boolean existsRating(Ratings ratings) {
        return (ratingsMapper.getRatingByUserIdAndMovieId(ratings.getUserId(),ratings.getMovieId()) != null);
    }

    @Override
    public Ratings insertRating(Ratings ratings) {
        ratingsMapper.addRating(ratings);
        return ratings;
    }
}
