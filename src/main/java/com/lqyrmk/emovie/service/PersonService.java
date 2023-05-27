package com.lqyrmk.emovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.emovie.entity.Person;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/5/27 16:45
 */
public interface PersonService extends IService<Person> {


    List<Person> getPersonByKey(String personKey, Integer limit);
}
