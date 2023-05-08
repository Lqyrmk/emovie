package com.lqyrmk.emovie.service;

import com.lqyrmk.emovie.entity.Ratings;
import com.lqyrmk.emovie.mapper.RatingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/3/31 23:28
 * @Description: RatingsService
 * @Version 1.0.0
 */
public interface RatingsService {

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


}
