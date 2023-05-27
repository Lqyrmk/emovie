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
 * @Date 2023/5/25 16:36
 */
@ApiModel("演员信息")
@Data
@TableName("cast")
public class Cast {

    @ApiModelProperty("演员id")
    @TableId("id")
    private Long castId;

    @ApiModelProperty("电影中的演员id")
    @TableField("cast_id")
    private Long castIdInMovie;

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("扮演角色名")
    private String characterInMovie;

    @ApiModelProperty("credit_id")
    private String creditId;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("身份id")
    private Long personId;

    @ApiModelProperty("演员姓名")
    private String castName;

    @ApiModelProperty("电影内排序")
    private Integer castOrder;

    @ApiModelProperty("主页地址")
    private String profilePath;

}
