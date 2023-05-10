package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.MovieGenre;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/9 22:36
 */
public interface MovieGenreService extends IService<MovieGenre>{

    /**
     * @description: 添加电影到类目中
     * @author: YuanmingLiu
     * @date: 2023/5/10 21:04
     * @param: [movieGenre]
     * @return: void
     **/
    void addMovieToGenre(MovieGenre movieGenre);

}
