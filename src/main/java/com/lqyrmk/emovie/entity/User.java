package com.lqyrmk.emovie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:53
 */
@ApiModel("用户")
@Data
public class User {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户手机号")
    private String phone;

}
