package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Cast;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/25 16:44
 */
public interface CastService extends IService<Cast> {

    List<Cast> getCastByKey(String castKey, Integer limit);
}
