package com.itheima.mapper;

import com.itheima.entity.Emp;
import com.itheima.entity.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 统计员工总记录数
     * @return
     */
    // @Select("select count(*) from emp")
    // Long count();

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id limit #{start}, #{pageSize}")
    // List<Emp> page(Integer start, Integer pageSize);

    /**
     * 列表查询
     * @return
     */
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id")
    // 基于XML开发--动态SQL
    List<Emp> list(EmpQueryParam param);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " + "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
}
