package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Cast;
import com.lqyrmk.emovie.mapper.CastMapper;
import com.lqyrmk.emovie.service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/25 16:45
 */
@Service
public class CastServiceImpl extends ServiceImpl<CastMapper, Cast> implements CastService {

    @Autowired
    private CastMapper castMapper;

    @Override
    public List<Cast> getCastByKey(String castKey, Integer limit) {
        // 模糊查询 按名称排序
        LambdaQueryWrapper<Cast> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Cast::getCastName, castKey)
                .orderByAsc(Cast::getCastName);

        // 分页，指定一次最多查出来的数量
        Page<Cast> page = new Page<>(1, limit);
        castMapper.selectPage(page, queryWrapper);
        List<Cast> casts = page.getRecords();

        return casts;
    }
}
