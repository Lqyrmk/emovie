package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Limo
 * @Date 2023/3/31 23:10
 */
@ApiModel("评分")
@Data
@TableName("ratings")
public class Ratings {

    @ApiModelProperty("id")
    @TableId
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("电影id")
    private Long movieId;

//    @ApiModelProperty("电影")
//    private Movie movie;

    @ApiModelProperty("评分")
    private Integer rating;

    @ApiModelProperty("评分时间戳")
    private Long timestamp;

}
