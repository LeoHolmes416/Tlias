package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

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

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID回显单个部门
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id} ")
    Dept listById(Integer id);

    /**
     * 修改部门
     */
    @Update("update dept set name = #{name} where id = #{id}")
    void update(Dept dept);
}
