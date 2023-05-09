package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Genre;

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
}
