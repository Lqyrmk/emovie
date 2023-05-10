package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.service.GenreService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/8 19:27
 */
@Api(tags = "类目接口")
@Slf4j
@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService genresService;

    @GetMapping
    @ApiOperation("查询所有电影类目")
    @ApiImplicitParams({

    })
    public Result<List<Genre>> getAllGenres() {
        List<Genre> list = genresService.list();
        return Result.success(list, "查询成功！");
    }

    /**
     * @description: 添加电影类目
     * @author: Limo
     * @date: 2023/4/1 17:07
     * @param: [com.lqyrmk.emovie.entity.Genre]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Genre>
     */
    @PostMapping
    @ApiOperation("添加电影类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "genre", value = "类目信息", required = true)
    })
    public Result<Genre> addGenres(@RequestBody Genre genre){
        // 查询类目是否存在
        LambdaQueryWrapper<Genre> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Genre::getGenreName, genre.getGenreName());
        if (genresService.getOne(queryWrapper) != null) {
            return Result.error("类目已存在，添加失败！");
        }
        // 类目不存在则保存类目
        genresService.save(genre);
        return Result.success(genre, "添加成功！");
    }

}
