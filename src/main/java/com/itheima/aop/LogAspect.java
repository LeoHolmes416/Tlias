package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect  //切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.itheima.anno.Log)") //切入点表达式。匹配方法上有Log注解的方法
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {  //环绕通知，调用目标方法运行

        //操作人ID（当前登录员工）
        //获取请求头中的令牌并且解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);  //存放自定义数据为一个map集合
        Integer operateUser = (Integer) claims.get("id"); //强转

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //获取操作对应的目标类的类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数,得到的是一个数组
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();  //记录开始执行时间
        //调用原始目标方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();  //记录执行结束时间

        //方法执行的返回值
        String returnValue = JSONObject.toJSONString(result);  //object结果类转json

        //方法操作耗时
        long costTime = end - begin;

        //记录操作日志
        //全参构造将参数封装到对象中
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}",operateLog);
        return result;
    }

}
