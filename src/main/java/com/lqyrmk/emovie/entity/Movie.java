package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 15:40
 * @Description: Movie
 * @Version 1.0.0
 */
@ApiModel("电影")
@Data
@TableName("movie")
public class Movie {

    @ApiModelProperty("电影id")
    @TableId
    private Long movieId;

    @ApiModelProperty("电影名")
    private String title;

    @ApiModelProperty("电影原名")
    private String originalTitle;

    @ApiModelProperty("电影原语言")
    private String originalLanguage;

    @ApiModelProperty("电影标语")
    private String tagline;

    @ApiModelProperty("电影概述")
    private String overview;

    @ApiModelProperty("电影时长")
    private String runtime;

    @ApiModelProperty("电影状态")
    private String status;

    @ApiModelProperty("发行时间")
    private String releaseDate;

    @ApiModelProperty("电影主页")
    private String homepage;

    @ApiModelProperty("电影热度")
    private String popularity;

    @ApiModelProperty("电影收入")
    private String revenue;

    @ApiModelProperty("电影分级")
    private String adult;

    @ApiModelProperty("电影预算")
    private String budget;

    @ApiModelProperty("电影平均投票")
    private String voteAverage;

    @ApiModelProperty("电影投票人数")
    private String voteCount;

    @ApiModelProperty("平均评分")
    private Double ratingAverage;

    @ApiModelProperty("制作国家")
    private List<MovieCountry> movieCountryList;

    @ApiModelProperty("所属类目")
    private List<MovieGenre> movieGenreList;

    @ApiModelProperty("语言")
    private List<MovieLanguage> movieLanguageList;

    @ApiModelProperty("default")
    private String imdbId;

    @ApiModelProperty("default")
    private String posterPath;

    @ApiModelProperty("default")
    private String video;
}
