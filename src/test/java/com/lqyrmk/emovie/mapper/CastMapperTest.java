package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.emovie.entity.Cast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/28 16:45
 */
@SpringBootTest
class CastMapperTest {

    @Autowired
    private CastMapper castMapper;

    @Test
    void getAllMovieByStep2() {
        System.out.println("cast = " + castMapper.getAllMovieByStep2(1L));

    }
}
