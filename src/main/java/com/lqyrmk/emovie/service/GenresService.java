package com.lqyrmk.emovie.service;

import com.lqyrmk.emovie.entity.Genres;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:59
 * @Description: GenresService
 * @Version 1.0.0
 */
public interface GenresService {

    /**
     * @description: 增加类目
     * @author: Limo
     * @date: 2023/4/1 17:00
     * @param: [com.lqyrmk.emovie.entity.Genres]
     * @return: boolean
     */
    public Genres insertGenre(Genres genres);
}
