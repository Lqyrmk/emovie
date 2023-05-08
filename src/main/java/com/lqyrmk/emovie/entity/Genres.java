package com.lqyrmk.emovie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 16:52
 * @Description: Genres
 * @Version 1.0.0
 */

@ApiModel("类目")
@Data
public class Genres {

    @ApiModelProperty("类目id")
    private Integer genreId;

    @ApiModelProperty("类目名")
    private Integer genreName;
}
