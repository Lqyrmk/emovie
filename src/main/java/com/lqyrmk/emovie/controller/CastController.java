package com.lqyrmk.emovie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Cast;
import com.lqyrmk.emovie.service.CastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/25 16:57
 */
@Api(tags = "演员接口")
@Slf4j
@RestController
@RequestMapping("/cast")
public class CastController {

    @Autowired
    private CastService castService;

//    @GetMapping
//    @ApiOperation("获取所有演员")
//    @ApiImplicitParams({
//    })
//    public Result<List<Cast>> getCastsByKey(@RequestParam(value = "castKey", required = false) String castKey,
//                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
//        // 传了key，包括empty
//        if (castKey != null) {
//            return Result.success(castService.getCastByKey(castKey, limit), "查询成功！");
//        }
//        // 未传key
//        else {
//            return Result.success(castService.list(), "查询成功！");
//        }
//    }



}
