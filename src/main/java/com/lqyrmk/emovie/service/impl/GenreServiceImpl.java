package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.mapper.GenreMapper;
import com.lqyrmk.emovie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:01
 * @Description: GenreServiceImpl
 * @Version 1.0.0
 */
@Service
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre> implements GenreService {

    @Autowired
    private GenreMapper genresMapper;

    @Override
    public Genre insertGenre(Genre genre) {
        genresMapper.addGenre(genre);
        return genre;
    }
}
