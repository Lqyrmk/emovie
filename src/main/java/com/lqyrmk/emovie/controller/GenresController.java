package com.lqyrmk.emovie.controller;

import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Genres;
import com.lqyrmk.emovie.service.GenresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/8 19:27
 */
@Api(tags = "类目接口")
@Slf4j
@RestController
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    /**
     * @description: 添加电影类目
     * @author: Limo
     * @date: 2023/4/1 17:07
     * @param: [com.lqyrmk.emovie.entity.Genres]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Genres>
     */
    @PostMapping
    @ApiOperation(value = "添加电影类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "genre", value = "类目信息", required = true)
    })
    public Result<Genres> addGenres(@RequestBody Genres genres){
        genresService.insertGenre(genres);
        return Result.success(genres);
    }

}
