package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.entity.Emp;
import com.itheima.entity.EmpExpr;
import com.itheima.entity.EmpQueryParam;
import com.itheima.entity.PageBean;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     * 分页查询
     * 
     * @param page     页码 1、2、3
     * @param pageSize 每页记录数 5、10
     * @return
     */
    // @Override
    // public PageBean page(Integer page, Integer pageSize) {
    // //1.调用mapper获取总记录数 total
    // // select count(*) from emp;
    // Long total = empMapper.count();
    //
    // //2.调用mapper获取分页列表数据 rows
    // //select e.name, e.gender,e.image,d.name,e.job from emp e left join dept d on
    // e.dept_id = d.id limit 0, 5;
    // Integer start = (page - 1) * pageSize; //计算起始索引
    // List<Emp> empList = empMapper.page(start, pageSize);
    //
    // //3.封装PageBean对象并返回
    // return new PageBean(total, empList);
    // }

    // @Override
    // public PageBean page(Integer page, Integer pageSize) {
    // //1.设置分页参数
    // PageHelper.startPage(page, pageSize);
    //
    // //2.调用mapper的列表查询方法
    // List<Emp> empList = empMapper.list();
    // Page p = (Page) empList; //强转对象，Page继承了ArrayList
    //
    // //分页只会对PageHelper.startPage()下面的第一条select进行处理
    // // List<Emp> empList2 = empMapper.list();
    //
    // //3.封装PageBean对象并返回
    // return new PageBean(p.getTotal(), p.getResult());
    // // return new PageBean(p.getTotal(), p);
    // // return new PageBean(p.getTotal(), empList);
    // }

    @Override
    public PageBean page(EmpQueryParam param) {
        // //1.设置分页参数
        // PageHelper.startPage(param.getPage(), param.getPageSize());

        // //2.调用mapper的列表查询方法
        // List<Emp> empList = empMapper.list(param);
        // Page p = (Page) empList; //强转对象，Page继承了ArrayList

        // //3.封装PageBean对象并返回
        // return new PageBean(p.getTotal(), p.getResult());

        // 1.设置分页参数
        System.out.println("param: " + param);
        PageHelper.startPage(param.getPage(), param.getPageSize());

        // 2.调用mapper的列表查询方法
        List<Emp> empList = empMapper.list(param);
        PageInfo<Emp> pageInfo = new PageInfo<>(empList);

        // 3.封装PageBean对象并返回
        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void save(Emp emp) {
        //1.补全基础属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //2.保存员工基本信息
        empMapper.insert(emp);
        //3. 保存员工的工作经历信息 - 批量
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }
}
