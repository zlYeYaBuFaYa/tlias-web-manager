package com.itheima.controller;

import com.itheima.entity.Clazz;
import com.itheima.entity.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理
 */
@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 新增班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 查询全部班级
     */
    @GetMapping("/list")
    public Result findAll(){
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

}
