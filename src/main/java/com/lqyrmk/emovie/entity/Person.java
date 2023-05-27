package com.lqyrmk.emovie.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/27 16:41
 */
@ApiModel("人员")
@Data
@TableName("person")
public class Person {

    @ApiModelProperty("身份id")
    @TableId
    private Long personId;

    @ApiModelProperty("姓名")
    private String castName;

}
