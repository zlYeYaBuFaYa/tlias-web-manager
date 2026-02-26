package com.itheima.service.impl;

import com.itheima.entity.Clazz;
import com.itheima.mapper.ClazzMapper;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public void save(Clazz clazz) {
        //1.补充基础属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.调用mapper
        clazzMapper.insert(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

}
