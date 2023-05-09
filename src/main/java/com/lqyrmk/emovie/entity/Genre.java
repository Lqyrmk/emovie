package com.lqyrmk.emovie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:52
 * @Description: Genre
 * @Version 1.0.0
 */

@ApiModel("类目")
@Data
public class Genre {

    @ApiModelProperty("类目id")
    private Long genreId;

    @ApiModelProperty("类目名")
    private String genreName;
}
