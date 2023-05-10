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
 * @Date 2023/5/9 21:15
 */
@ApiModel("国家")
@Data
@TableName("country")
public class Country {

    @ApiModelProperty("国家id")
    @TableId
    private Long countryId;

    @ApiModelProperty("iso_3166_1")
    @TableField("iso_3166_1")
    private String iso;

    @ApiModelProperty("国家名")
    @TableField("country_name")
    private String countryName;

}
