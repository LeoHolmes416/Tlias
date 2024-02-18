package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Component
//@Aspect  //AOP类
public class TimeAspect {
    @Around("execution(* com.itheima.service.*.*(..))") //切入点表达式，表示当前共性的功能需要加在哪些方法上
    //@Around("com.itheima.aop.MyAspect1.pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable { //通知
        //1.记录开始时间
        long begin = System.currentTimeMillis();
        //2.调用原始方法,得到原始方法的返回值
        Object result = joinPoint.proceed();
        //3.记录结束时间，计算执行方法耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature()+"方法执行耗时: {}ms",end-begin);
        return result;
    }
}
