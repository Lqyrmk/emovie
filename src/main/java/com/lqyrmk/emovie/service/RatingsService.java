package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.mapper.RatingsMapper;

import java.util.Map;

/**
 * @Auther: Limo
 * @Date: 2023/3/31 23:28
 * @Description: RatingsService
 * @Version 1.0.0
 */
public interface RatingsService extends IService<Ratings> {

    /**
     * @description: 新增评分
     * @author: YuanmingLiu
     * @date: 2023/5/11 19:30
     * @param: [ratings]
     * @return: int
     **/
    int addRatings(Ratings ratings);

    /**
     * @description: 根据id查询评分
     * @author: YuanmingLiu
     * @date: 2023/6/11 0:07
     * @param: [userId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getRatingsByUserId(Long userId);
}
