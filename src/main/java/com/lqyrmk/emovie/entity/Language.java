package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/13 0:08
 */
@ApiModel("电影语言")
@Data
@TableName("language")
public class Language {

    @ApiModelProperty("语言id")
    @TableId
    private Long languageId;

    @ApiModelProperty("iso_639_1")
    @TableField("iso_639_1")
    private String iso;

    @ApiModelProperty("语言名")
    @TableField("language_name")
    private String languageName;

}
