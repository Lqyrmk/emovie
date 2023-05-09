package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/9 22:29
 */
@ApiModel("电影-类目对应关系")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("movie_genres")
public class MovieGenre {

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("类目id")
    private Long genresId;


}
