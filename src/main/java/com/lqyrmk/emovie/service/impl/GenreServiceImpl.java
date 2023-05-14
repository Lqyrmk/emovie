package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Country;
import com.lqyrmk.emovie.entity.Genre;
import com.lqyrmk.emovie.mapper.GenreMapper;
import com.lqyrmk.emovie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:01
 * @Description: GenreServiceImpl
 * @Version 1.0.0
 */
@Service
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre> implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public Genre insertGenre(Genre genre) {
        genreMapper.addGenre(genre);
        return genre;
    }

    @Override
    public List<Genre> getGenresByKey(String genreKey, Integer limit) {

        // 模糊查询 按名称排序
        LambdaQueryWrapper<Genre> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Genre::getGenreName, genreKey)
                .orderByAsc(Genre::getGenreName);

        // 分页，指定一次最多查出来的数量
        Page<Genre> page = new Page<>(1, limit);
        genreMapper.selectPage(page, queryWrapper);
        List<Genre> genres = page.getRecords();

        return genres;
    }
}
