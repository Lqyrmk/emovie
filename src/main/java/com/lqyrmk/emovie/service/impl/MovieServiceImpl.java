package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.*;
import com.lqyrmk.emovie.mapper.*;
import com.lqyrmk.emovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:21
 * @Description: MovieServiceImpl
 * @Version 1.0.0
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private MovieCountryMapper movieCountryMapper;


    @Override
    public Country getAllMovies() {
//    public List<Movie> getAllMovies() {

//        LambdaQueryWrapper<Country> countryLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        countryLambdaQueryWrapper
//                .select(Country::getCountryId)
//                .eq(Country::getCountryName, "Finland");
//        Country country = countryMapper.selectOne(countryLambdaQueryWrapper);
//
//        LambdaQueryWrapper<MovieCountry> movieCountryLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        movieCountryLambdaQueryWrapper
//                .select(MovieCountry::getMovieId, MovieCountry::getCountryId)
//                .eq(MovieCountry::getCountryId, country.getCountryId());
//
//
//        List<MovieCountry> movieCountries = movieCountryMapper.selectList(movieCountryLambdaQueryWrapper);
//        System.out.println("movieCountries = " + movieCountries);
//        List<Long> idList = new ArrayList<>();
//        for (MovieCountry item : movieCountries) {
//            idList.add(item.getMovieId());
//        }
//
//        LambdaQueryWrapper<Movie> movieLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        movieLambdaQueryWrapper
//                .select(Movie::getMovieId, Movie::getTitle, Movie::getOriginalTitle, Movie::getOriginalLanguage
//                        , Movie::getTagline, Movie::getRuntime, Movie::getReleaseDate, Movie::getPopularity, Movie::getVoteAverage)
//                .in(Movie::getMovieId, idList);
//        List<Movie> movies = movieMapper.selectList(movieLambdaQueryWrapper);
//        return movies;

        // 反向测试
        Country country = countryMapper.getAllCountryAndMovieByStep1("Finland");
//        System.out.println("country = " + country);
        return country;

        // 正向测试
//        return movieMapper.getAllMovieAndCountryByStep1("Finland");
    }

    @Override
//    public Map<String, Object> getMoviesByPage(Integer current,
    public Page<Movie> getMoviesByPage(Integer current,
                                               Integer size,
                                               String movieNameKey,
                                               String countryName,
                                               String genreName,
                                               String languageIso,
                                               String year,
                                               String rating) {
        Page<Movie> page = new Page<>(current, size);
        movieMapper.getAllMovieAndCountryByStep1(page, movieNameKey, countryName, genreName, languageIso, year, rating);

//        Map<String, Object> moviesMap = new HashMap<>();
//        // 查询出的记录
//        moviesMap.put("records", page.getRecords());
//        // 总页数
//        moviesMap.put("pages", page.getPages());
//        // 总记录数
//        moviesMap.put("total", page.getTotal());
//        // 当前页的页码
//        moviesMap.put("current", page.getCurrent());
//        // 每页显示的数据条数
//        moviesMap.put("size", page.getSize());
//        // 有下一页
//        moviesMap.put("hasNext", page.hasNext());
//        // 有上一页
//        moviesMap.put("hasPrevious", page.hasPrevious());

        return page;
    }


    @Override
    public Movie getMovieById(Long movieId) {
        return movieMapper.getMovieAndCountryByStep1(movieId, "Finland");
    }

    @Override
    public List<String> getMovieByNameKey(String movieNameKey) {

        // 模糊查询 按名称排序
        LambdaQueryWrapper<Movie> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Movie::getTitle).like(Movie::getTitle, movieNameKey).orderByAsc(Movie::getTitle);

        // 分页，一次最多查出来10个
        Page<Map<String, Object>> page = new Page<>(1, 10);
        movieMapper.selectMapsPage(page, queryWrapper);

        // 使用一个数组对查询出来的电影名进行存放
        List<String> movieNameList = new ArrayList<>();
        for (Map<String, Object> record : page.getRecords()) {
            movieNameList.add((String) record.get("title"));
        }

        return movieNameList;
    }

    @Override
    public Movie insertMovie(Movie movie) {
        movieMapper.addMovie(movie);
        return movie;
    }
}
