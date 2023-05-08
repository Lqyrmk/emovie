package com.lqyrmk.emovie.mapper;

import com.lqyrmk.emovie.entity.Movie;
import org.apache.ibatis.annotations.*;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:23
 * @Description: MovieMapper
 * @Version 1.0.0
 */

@Mapper
public interface MovieMapper {

    void addMovie(Movie movie);
}
