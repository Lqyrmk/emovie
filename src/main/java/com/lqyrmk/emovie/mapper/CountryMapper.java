package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/10 11:04
 */
@Mapper
public interface CountryMapper extends BaseMapper<Country> {

    /**
     * @description: 分步查询所有电影完整信息的第三步
     * @author: YuanmingLiu
     * @date: 2023/5/10 14:56
     * @param: [countryId, countryKey]
     * @return: com.lqyrmk.emovie.entity.Country
     **/
    Country getAllMovieByStep3(@Param("countryId") Long countryId);

    /**
     * @description: 根据电影id分步查询电影和电影制片国家信息的第三步
     * @author: YuanmingLiu
     * @date: 2023/5/10 12:33
     * @param: [countryId]
     * @return: com.lqyrmk.emovie.entity.Country
     **/
    Country getMovieAndCountryByStep3(@Param("countryId") Long countryId,
                                      @Param("countryKey") String countryKey);


    /**
     * @description: 反向测试第一步
     * @author: YuanmingLiu
     * @date: 2023/5/10 15:43
     * @param: [countryKey]
     * @return: com.lqyrmk.emovie.entity.Country
     **/
    Country getAllCountryAndMovieByStep1(@Param("countryKey") String countryKey);

}
