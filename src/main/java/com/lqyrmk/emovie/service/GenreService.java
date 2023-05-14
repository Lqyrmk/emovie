package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Genre;

import java.util.List;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:59
 * @Description: GenreService
 * @Version 1.0.0
 */
public interface GenreService extends IService<Genre> {

    /**
     * @description: 增加类目
     * @author: Limo
     * @date: 2023/4/1 17:00
     * @param: [com.lqyrmk.emovie.entity.Genre]
     * @return: boolean
     */
    public Genre insertGenre(Genre genres);

    /**
     * @description: 根据关键词查询类目
     * @author: YuanmingLiu
     * @date: 2023/5/14 13:06
     * @param: [genreKey, limit]
     * @return: java.util.List<com.lqyrmk.emovie.entity.Genre>
     **/
    List<Genre> getGenresByKey(String genreKey, Integer limit);
}
