package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部的部门信息
     * 简单语句使用查询开发
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();
}
