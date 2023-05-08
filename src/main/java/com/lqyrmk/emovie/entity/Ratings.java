package com.lqyrmk.emovie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Limo
 * @Date: 2023/3/31 23:10
 * @Description: Ratings
 * @Version 1.0.0
 */

@ApiModel("评分")
@Data
public class Ratings {
    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("电影id")
    private Integer movieId;

    @ApiModelProperty("评分")
    private Integer rating;

    @ApiModelProperty("评分")
    private Integer timestamp;

}
