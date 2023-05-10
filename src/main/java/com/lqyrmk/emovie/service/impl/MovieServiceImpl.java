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
import java.util.List;

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
    public List<Movie> getMoviesByPage(Integer current, Integer size, String movieNameKey, String countryName, String genreName) {
        Page<Movie> page = new Page<>(current, size);
        movieMapper.getAllMovieAndCountryByStep1(page, movieNameKey, countryName, genreName);
        List<Movie> records = page.getRecords();
        return records;
    }


    @Override
    public Movie getMovieById(Long movieId) {
        return movieMapper.getMovieAndCountryByStep1(movieId, "Finland");
    }

    @Override
    public Movie insertMovie(Movie movie) {
        movieMapper.addMovie(movie);
        return movie;
    }
}
