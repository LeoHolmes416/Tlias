package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j  //lombok注解
@RequestMapping("/login")  //添加类的公共路径
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("员工登录信息: {}",emp);
        Emp e = empService.login(emp);
        //return e != null?Result.success():Result.error("用户名或密码错误");

        //登陆成功，生成并下发令牌,由前端存储到用户浏览器本地
        if(e != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);  //此时生成的jwt包含了当前登陆的员工信息
            return Result.success(jwt);
        }
        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
