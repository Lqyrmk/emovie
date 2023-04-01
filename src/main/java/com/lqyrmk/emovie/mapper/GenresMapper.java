package com.lqyrmk.emovie.mapper;

import com.lqyrmk.emovie.entity.Genres;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:58
 * @Description: GenresMapper
 * @Version 1.0.0
 */

@Mapper
public interface GenresMapper {
    void addGenre(Genres genres);
}
