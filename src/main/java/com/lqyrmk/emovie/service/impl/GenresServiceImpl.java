package com.lqyrmk.emovie.service.impl;

import com.lqyrmk.emovie.entity.Genres;
import com.lqyrmk.emovie.mapper.GenresMapper;
import com.lqyrmk.emovie.service.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:01
 * @Description: GenresServiceImpl
 * @Version 1.0.0
 */
@Service
public class GenresServiceImpl implements GenresService {

    @Autowired
    private GenresMapper genresMapper;

    @Override
    public Genres insertGenre(Genres genres) {
        genresMapper.addGenre(genres);
        return genres;
    }
}
