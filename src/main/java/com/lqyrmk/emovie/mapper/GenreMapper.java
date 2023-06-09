package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:58
 * @Description: GenreMapper
 * @Version 1.0.0
 */

@Mapper
public interface GenreMapper extends BaseMapper<Genre> {
    void addGenre(Genre genres);

    /**
     * @description: 分步查询所有电影完整信息的第三步
     * @author: YuanmingLiu
     * @date: 2023/5/10 20:25
     * @param: [genreId]
     * @return: com.lqyrmk.emovie.entity.Genre
     **/
    Genre getAllMovieByStep3(@Param("genreId") Long genreId);

    /**
     * @description: 根据电影id分步查询电影完整信息的第三步
     * @author: YuanmingLiu
     * @date: 2023/5/10 20:25
     * @param: [genreId]
     * @return: com.lqyrmk.emovie.entity.Genre
     **/
    Genre getMovieByIdByStep3(@Param("genreId") Long genreId);

}
