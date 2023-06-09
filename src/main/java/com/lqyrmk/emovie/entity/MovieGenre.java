package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("movie_genre")
public class MovieGenre {

    @ApiModelProperty("id")
    @TableId
    private Long id;

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("类目id")
    private Long genreId;

    @ApiModelProperty("类目")
    private Genre genre;

}
