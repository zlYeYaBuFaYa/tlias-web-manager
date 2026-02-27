package com.itheima.service;

import com.itheima.entity.Emp;
import com.itheima.entity.EmpQueryParam;
import com.itheima.entity.PageBean;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(EmpQueryParam param);
    void save(Emp emp);

}
