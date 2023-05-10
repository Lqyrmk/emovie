package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 电影-国家对应关系
 * @Author YuanmingLiu
 * @Date 2023/5/9 21:11
 */
@ApiModel("电影-国家对应关系")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("movie_country")
public class MovieCountry {

    @ApiModelProperty("id")
    @TableId
    private Long id;

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("国家")
    private Long countryId;
    //1
    private Country country;

    //2
//    private Movie movie;

}
