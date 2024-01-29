package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j  //lombok注解
@RequestMapping("/emps")  //添加类的公共路径
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    /**
     * 条件分页查询
     * 参数：分页：1.当前页面页码page 2.当前页面每页有多少个pageSize
     *      查询：1.姓名name 2.性别gender 3.查询起始时间begin 4.查询终止时间end
     */
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数: {},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service方法查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    /**
     * 批量或单个删除员工操作
     * 参数：1.一组id：一组List格式的数据
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作：id{}",ids);
        //调用service方法删除
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 添加员工操作
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }
}
