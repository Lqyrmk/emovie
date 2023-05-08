package com.lqyrmk.emovie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 15:40
 * @Description: Movie
 * @Version 1.0.0
 */
@ApiModel("电影")
@Data
public class Movie {
    @ApiModelProperty("电影id")
    private Integer movieId;

    @ApiModelProperty("电影分级")
    private String adult;

    @ApiModelProperty("电影预算")
    private String budget;

    @ApiModelProperty("电影主页")
    private String homepage;

    @ApiModelProperty("default")
    private String imdbId;

    @ApiModelProperty("原语言")
    private String origLang;

    @ApiModelProperty("电影原名")
    private String origTitle;

    @ApiModelProperty("电影概述")
    private String overview;

    @ApiModelProperty("电影热度")
    private String popularity;

    @ApiModelProperty("default")
    private String posterPath;

    @ApiModelProperty("上映时间")
    private String releaseDate;

    @ApiModelProperty("电影收入")
    private String revenue;

    @ApiModelProperty("电影时长")
    private String runtime;

    @ApiModelProperty("电影状态")
    private String status;

    @ApiModelProperty("电影标语")
    private String tagline;

    @ApiModelProperty("电影名")
    private String title;

    @ApiModelProperty("default")
    private String video;

    @ApiModelProperty("电影平均评分")
    private String voteAverage;

    @ApiModelProperty("电影评分人数")
    private String voteCount;
}