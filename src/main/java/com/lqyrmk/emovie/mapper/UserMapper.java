package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:48
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
