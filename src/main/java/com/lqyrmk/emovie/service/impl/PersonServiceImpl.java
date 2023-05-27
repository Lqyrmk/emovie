package com.lqyrmk.emovie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.emovie.entity.Cast;
import com.lqyrmk.emovie.entity.Person;
import com.lqyrmk.emovie.mapper.PersonMapper;
import com.lqyrmk.emovie.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/27 16:46
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> getPersonByKey(String personKey, Integer limit) {
        // 模糊查询 按名称排序
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Person::getCastName, personKey)
                .orderByAsc(Person::getCastName);

        // 分页，指定一次最多查出来的数量
        Page<Person> page = new Page<>(1, limit);
        personMapper.selectPage(page, queryWrapper);
        List<Person> personList = page.getRecords();

        return personList;
    }
}
