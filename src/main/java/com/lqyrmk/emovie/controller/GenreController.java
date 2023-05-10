package com.lqyrmk.emovie.controller;

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
            @ApiImplicitParam(name = "genres", value = "类目信息", required = true)
    })
    public Result<Genre> addGenres(@RequestBody Genre genres){
        genresService.insertGenre(genres);
        return Result.success(genres);
    }

}
