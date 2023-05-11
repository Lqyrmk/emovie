package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Ratings;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: Limo
 * @Date: 2023/3/31 23:09
 * @Description: RatingsMapper
 * @Version 1.0.0
 */

@Mapper
public interface RatingsMapper extends BaseMapper<Ratings> {

    void addRating(Ratings ratings);

    Ratings getRatingByUserIdAndMovieId(Long userId, Long MovieId);

}
