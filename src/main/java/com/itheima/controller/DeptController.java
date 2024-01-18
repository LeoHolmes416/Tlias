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
@RestController
public class DeptController {
    //public static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired  //注入
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET ) //声明请求路径为/depts，声明请求方式为get
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        //调用service查询全部数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);  //这里的{}是参数占位符
        //调用service删除部门,该方法无返回值
        deptService.delete(id);
        return Result.success(); //无参方法
    }

}
