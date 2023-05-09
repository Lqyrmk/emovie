package com.lqyrmk.emovie.controller;

import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.service.RatingsService;
import com.lqyrmk.emovie.service.UserService;
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
 * @Date 2023/5/8 19:25
 */
@Api(tags = "评分接口")
@Slf4j
@RestController
@RequestMapping("ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    /**
     * @description: 用户评分
     * @author: Limo
     * @date: 2023/4/1 15:39
     * @param: [com.lqyrmk.emovie.entity.Ratings]
     * @return: com.lqyrmk.emovie.common.Result<com.lqyrmk.emovie.entity.Ratings>
     */
    @PostMapping
    @ApiOperation(value = "用户评分接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ratings", value = "用户评分信息", required = true)
    })
    public Result<Ratings> addRating(@RequestBody Ratings ratings) {
        if (ratingsService.existsRating(ratings)) {
            return Result.error("评分已存在");
        }
        ratingsService.insertRating(ratings);
        return Result.success(ratings);
    }
}
