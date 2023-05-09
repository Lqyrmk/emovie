package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Genre;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:58
 * @Description: GenreMapper
 * @Version 1.0.0
 */

@Mapper
public interface GenreMapper extends BaseMapper<Genre> {
    void addGenre(Genre genres);
}
