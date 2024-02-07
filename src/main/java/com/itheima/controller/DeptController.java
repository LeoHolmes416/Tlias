package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j  //lombok注解
@RequestMapping("/depts")  //添加类的公共路径
@RestController
public class DeptController {
    //public static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired  //注入
    private DeptService deptService;

    /**
     * 查询全部部门数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET ) //声明请求路径为/depts，声明请求方式为get
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        //调用service查询全部数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception{
        log.info("根据id删除部门:{}",id);  //这里的{}是参数占位符
        //调用service删除部门,该方法无返回值
        deptService.delete(id);
        return Result.success(); //无参方法
    }
    /**
     * 新增部门
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //json格式的参数封装到实体类中,注解用@RequestBody
        log.info("新增部门:{}",dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门，用于修改时回显
     */
    @GetMapping("/{id}")
    public Result listById(@PathVariable Integer id){
        log.info("根据id回显部门信息:{}",id);
        Dept dept = deptService.listById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public Result modify(@RequestBody Dept dept){
        log.info("修改部门:{}",dept);
        deptService.modify(dept);
        return Result.success();
    }
}
