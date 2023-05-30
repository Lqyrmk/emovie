package com.lqyrmk.emovie.mapper;

import com.lqyrmk.emovie.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 12:36
 */
@SpringBootTest
public class MovieMapperTest {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    void getMovieAndCountryByStep1() {
        Movie movie = movieMapper.getMovieByIdByStep1(16L);
        System.out.println("movie = " + movie);
    }
}
