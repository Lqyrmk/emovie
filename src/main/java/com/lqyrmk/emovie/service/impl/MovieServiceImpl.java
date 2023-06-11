package com.lqyrmk.emovie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.*;
import com.lqyrmk.emovie.mapper.*;
import com.lqyrmk.emovie.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:21
 * @Description: MovieServiceImpl
 * @Version 1.0.0
 */
@Slf4j
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private MovieCountryService movieCountryService;

    @Autowired
    private MovieLanguageService movieLanguageService;

    @Autowired
    private CastService castService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private RedisTemplate redisTemplate;

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
        movieMapper.getAllMovieByStep1(page, movieNameKey, countryName, genreName, languageIso, year, rating);

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
        return movieMapper.getMovieByIdByStep1(movieId);
    }

    @Override
//    public List<String> getMovieByNameKey(String movieNameKey, Integer limit) {
    public List<Movie> getMovieByNameKey(String movieNameKey, Integer limit) {

        // 模糊查询 按名称排序
        LambdaQueryWrapper<Movie> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Movie::getMovieId, Movie::getTitle)
                .like(Movie::getTitle, movieNameKey)
                .orderByAsc(Movie::getTitle);

        // 分页，指定一次最多查出来的数量
//        Page<Map<String, Object>> page = new Page<>(1, limit);
//        movieMapper.selectMapsPage(page, queryWrapper);

//        // 使用一个数组对查询出来的电影名进行存放
//        List<String> movieNameList = new ArrayList<>();
//        for (Map<String, Object> record : page.getRecords()) {
//            movieNameList.add((String) record.get("title"));
//        }

        // 分页，指定一次最多查出来的数量
        Page<Movie> page = new Page<>(1, limit);
        movieMapper.selectPage(page, queryWrapper);
        List<Movie> movies = page.getRecords();

        return movies;
    }

    @Override
    public Page<Movie> getPopularMovies(Integer current, Integer size) {
        Page<Movie> page = new Page<>(current, size);
        movieMapper.getPopularMovies(page);
        return page;
    }

    @Override
    public Page<Movie> getHotMovies(Integer current, Integer size) {
        Page<Movie> page = new Page<>(current, size);
        movieMapper.getHotMovies(page);
        return page;
    }

    // 处理添加电影时的国家信息
    private void handleInsertMovieByCountry(List<Long> countryIdList, Movie movie) {
        // 判断是否为空
        if (countryIdList != null) {
            // 建立电影和国家的联系
            List<MovieCountry> movieCountryList = new ArrayList<>();
            countryIdList.forEach(countryId -> {
                MovieCountry movieCountry = new MovieCountry();
                // 电影id 国家id
                movieCountry.setMovieId(movie.getMovieId());
                movieCountry.setCountryId(countryId);
                movieCountryList.add(movieCountry);
            });
            movieCountryService.saveBatch(movieCountryList);
        } else {
            // 为空则进行默认赋值，选第一个国家
            MovieCountry movieCountry = new MovieCountry();
            movieCountry.setMovieId(movie.getMovieId());
            movieCountry.setCountryId(1L);
            movieCountryService.save(movieCountry);
        }
    }

    // 处理添加电影时的语言信息
    private void handleInsertMovieByLanguage(List<Long> languageIdList, Movie movie) {
        // 判断是否为空
        if (languageIdList != null) {
            // 建立电影和语言的联系
            List<MovieLanguage> movieLanguageList = new ArrayList<>();
            languageIdList.forEach(languageId -> {
                MovieLanguage movieLanguage = new MovieLanguage();
                // 电影id 语言id
                movieLanguage.setMovieId(movie.getMovieId());
                movieLanguage.setLanguageId(languageId);
                movieLanguageList.add(movieLanguage);
            });
            movieLanguageService.saveBatch(movieLanguageList);
        } else {
            // 为空则进行默认赋值，选第一个语言
            MovieLanguage movieLanguage = new MovieLanguage();
            movieLanguage.setMovieId(movie.getMovieId());
            movieLanguage.setLanguageId(1L);
            movieLanguageService.save(movieLanguage);
        }
    }

    // 处理添加电影时的演员信息
    private Map<Long, String> handleInsertMovieByCast(List<Long> castIdList, List<String> newCastNameList, Movie movie) {

        // 判断手动输入的演员姓名列表是否为空
        if (newCastNameList != null) {
            newCastNameList.forEach(castName -> {
                // 先把手动输入的演员姓名添加到人员表中
                Person person = new Person();
                person.setCastName(castName);
                personMapper.insert(person);
                // 添加后获取到id，将其添加进id列表中
                castIdList.add(person.getPersonId());
            });
        }

        // 生成map
        Map<Long, String> castIdNameMap = castIdList.stream().collect(Collectors.toMap(castId -> castId, castId -> personMapper.selectById(castId).getCastName(), (val1, val2) -> val1));

        // 建立电影和演员的联系
        List<Cast> casts = new ArrayList<>();
        castIdNameMap.forEach((key, value) -> {
            Cast cast = new Cast();
            // 电影id 人员id
            cast.setMovieId(movie.getMovieId());
            cast.setPersonId(key);
            cast.setCastName(value);
            casts.add(cast);
        });
        castService.saveBatch(casts);

        return castIdNameMap;
    }

    // 处理添加电影时的导演信息
    private Map<Long, String> handleInsertMovieByDirector(List<Long> directorIdList, List<String> newDirectorNameList, Movie movie) {

        // 判断手动输入的导演姓名列表是否为空
        if (newDirectorNameList != null) {
            newDirectorNameList.forEach(directorName -> {
                // 先把手动输入的导演姓名添加到人员表中
                Person person = new Person();
                person.setCastName(directorName);
                personMapper.insert(person);
                // 添加后获取到id，将其添加进id列表中
                directorIdList.add(person.getPersonId());
            });
        }

        // 生成map
        Map<Long, String> crewIdNameMap = directorIdList.stream().collect(Collectors.toMap(directorId -> directorId, directorId -> personMapper.selectById(directorId).getCastName(), (val1, val2) -> val1));

        // 建立电影和导演的联系
        List<Crew> crews = new ArrayList<>();
        crewIdNameMap.forEach((key, value) -> {
            Crew crew = new Crew();
            //
            crew.setMovieId(movie.getMovieId());
            crew.setDepartment("Directing");
            crew.setJob("Director");
            crew.setPersonId(key);
            crew.setCrewName(value);
            crews.add(crew);
        });
        crewService.saveBatch(crews);

        return crewIdNameMap;
    }

    @Override
    public Map<String, Object> insertMovie(Map<String, Object> movieMap) {

        // 获取movie对象
        String movieJsonStr = JSONObject.toJSONString(movieMap.get("movie"));
        Movie movie = JSONObject.parseObject(movieJsonStr, Movie.class);

        // 添加电影信息
        // 获得id
        movieMapper.insert(movie);

        // 获取制片国家id构成的list对象
        List<Long> countryIdList = JSONObject.parseArray(JSONObject.toJSONString(movieMap.get("countryIdList")), Long.class);
        handleInsertMovieByCountry(countryIdList, movie);

        // 获取电影语言id构成的list对象
        List<Long> languageIdList = JSONObject.parseArray(JSONObject.toJSONString(movieMap.get("languageIdList")), Long.class);
        handleInsertMovieByLanguage(languageIdList, movie);

        // 获取电影演员id构成的list对象
        List<Long> castIdList = JSONObject.parseArray(JSONObject.toJSONString(movieMap.get("castIdList")), Long.class);
        if (castIdList == null) {
            castIdList = new ArrayList<>();
        }
        // 获取电影演员姓名构成的list对象
        List<String> newCastNameList = (List<String>) movieMap.get("newCastNameList");

        Map<Long, String> castIdNameMap = handleInsertMovieByCast(castIdList, newCastNameList, movie);


        // 获取电影导演id构成的list对象
        List<Long> directorIdList = JSONObject.parseArray(JSONObject.toJSONString(movieMap.get("directorIdList")), Long.class);
        if (directorIdList == null) {
            directorIdList = new ArrayList<>();
        }
        // 获取电影导演姓名构成的list对象
        List<String> newDirectorNameList = (List<String>) movieMap.get("newDirectorNameList");

        Map<Long, String> directorIdNameMap = handleInsertMovieByDirector(directorIdList, newDirectorNameList, movie);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("movieId", movie.getMovieId());
        resMap.put("countryIdList", countryIdList);
        resMap.put("languageIdList", languageIdList);
        resMap.put("castIdNameMap", castIdNameMap);
        resMap.put("directorIdNameMap", directorIdNameMap);

        return resMap;
    }

    @Override
    public Page<Movie> getRecommendMovies(Integer current, Integer size) {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        Page<Movie> page = new Page<>(current, size);
        String redisKey = "recommend:top_n_movie:user:" + userId;
        List<Object> recommendMovieList = redisTemplate.opsForList().range(redisKey, 0, -1);
        if (recommendMovieList.isEmpty()) {
            redisKey = "recommend:top_n_movie:user:1";
            recommendMovieList = redisTemplate.opsForList().range(redisKey, 0, -1);
        }
        Long[] ids = new Long[recommendMovieList.size()];
        for (int i = 0; i < recommendMovieList.size(); i++) {
            ids[i] = Long.parseLong(recommendMovieList.get(i).toString());
        }
        movieMapper.getRecommendMovies(page, ids);
        return page;
    }
}
