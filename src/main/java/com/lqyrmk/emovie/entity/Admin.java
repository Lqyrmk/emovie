package com.lqyrmk.emovie.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:08
 * @Description: Admin
 * @Version 1.0.0
 */

@ApiModel("管理员")
@Data
public class Admin {
    @ApiModelProperty("管理员id")
    private Integer adminId;

    @ApiModelProperty("管理员用户名")
    private String name;

    @ApiModelProperty("管理员密码")
    private String password;

}
