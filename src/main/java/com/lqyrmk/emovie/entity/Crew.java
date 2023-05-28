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
 * @Date 2023/5/27 21:37
 */
@ApiModel("工作人员信息")
@Data
@TableName("crew")
public class Crew {

    @ApiModelProperty("工作人员id")
    @TableId("id")
    private Long crewId;

    @ApiModelProperty("电影id")
    private Long movieId;

    @ApiModelProperty("credit_id")
    private String creditId;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("人员id")
    private Long personId;

    @ApiModelProperty("职务")
    private String job;

    @ApiModelProperty("工作人员姓名")
    private String crewName;

    @ApiModelProperty("主页地址")
    private String profilePath;


}
