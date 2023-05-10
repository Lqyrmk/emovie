package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 电影-国家对应关系
 * @Author YuanmingLiu
 * @Date 2023/5/9 21:11
 */
@ApiModel("电影-国家对应关系")
@Data
@TableName("movie_country")
public class MovieCountry {

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("国家id")
    private Long countryId;

}