package com.lqyrmk.emovie.controller;

import com.lqyrmk.emovie.common.Result;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.entity.Person;
import com.lqyrmk.emovie.service.PersonService;
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
 * @Date 2023/5/27 16:44
 */
@Api(tags = "人员接口")
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping
    @ApiOperation("根据关键词查询人员")
    @ApiImplicitParams({
    })
    public Result<List<Person>> getPersonByKey(@RequestParam(value = "personKey", required = false) String personKey,
                                               @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        // 传了key，包括empty
        if (personKey != null) {
            return Result.success(personService.getPersonByKey(personKey, limit), "查询成功！");
        }
        // 未传key
        else {
            return Result.success(personService.list(), "查询成功！");
        }
    }


    }
