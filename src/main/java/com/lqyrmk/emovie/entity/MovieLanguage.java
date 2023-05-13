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
 * @Date 2023/5/13 17:36
 */
@ApiModel("电影-语言对应关系")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("movie_language")
public class MovieLanguage {

    @ApiModelProperty("id")
    @TableId
    private Long id;

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("语言id")
    private Long languageId;

    @ApiModelProperty("语言")
    private Language language;

}
