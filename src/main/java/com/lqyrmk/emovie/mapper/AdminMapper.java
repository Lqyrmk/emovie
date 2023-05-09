package com.lqyrmk.emovie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.emovie.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: Limo
 * @Date: 2023/4/1 17:15
 * @Description: AdminMapper
 * @Version 1.0.0
 */

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
