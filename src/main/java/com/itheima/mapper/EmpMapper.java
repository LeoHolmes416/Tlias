package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     * @return
     */
    /*
    @Select("select count(*) from emp")
    public Long count();
    */
    /**
     * 分页查询，获取列表数据
     * @param start
     * @param pageSize
     * @return
     */
    /*
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize);
    */

    /**
     * 分页插件实现员工信息查询
     *
     * @return
     */
    //@Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据id批量删除员工数据
     *
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 员工回显
     */
    @Select("Select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 登陆时用户名和密码判断
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUserNameAndPwd(Emp emp);

    /**
     * 根据部门删除对应员工
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
