package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.mapper.RatingsMapper;

/**
 * @Auther: Limo
 * @Date: 2023/3/31 23:28
 * @Description: RatingsService
 * @Version 1.0.0
 */
public interface RatingsService extends IService<Ratings> {

    /**
     * @description: 判断是否存在评分
     * @author: Limo
     * @date: 2023/4/1 12:52
     * @param: [com.lqyrmk.emovie.entity.Ratings]
     * @return: boolean
     */
    public boolean existsRating(Ratings ratings);

    /**
     * @description: 增加评分
     * @author: Limo
     * @date: 2023/4/1 12:53
     * @param: [com.lqyrmk.emovie.entity.Ratings]
     * @return: com.lqyrmk.emovie.entity.Ratings
     */
    public Ratings insertRating(Ratings ratings);

    /**
     * @description: 新增评分
     * @author: YuanmingLiu
     * @date: 2023/5/11 19:30
     * @param: [ratings]
     * @return: void
     **/
    void addRatings(Ratings ratings);
}
