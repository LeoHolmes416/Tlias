package com.itheima.service;

import com.itheima.pojo.DeptLog;

/**
 * 由于日志记录无论是否事务回滚都需要进行
 * 故新建service以确保日志和删除操作不统属于一个事务
 */
public interface DeptLogService {

    void insert(DeptLog deptLog);
}
