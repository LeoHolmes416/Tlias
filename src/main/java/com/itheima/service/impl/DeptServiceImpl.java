package com.itheima.service.impl;

import com.itheima.mapper.DeptLogMapper;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.pojo.Emp;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * 根据id删除部门，并且删除所有该部门下的员工
     * 模拟抛出异常，并且回滚程序
     * 无论是否抛出异常，都记录并保存日志到数据库中
     * @param id
     * @throws Exception
     */
    @Override
    @Transactional
    //@Transactional(rollbackFor = Exception.class)  //捕获所有类型的异常，并且回滚所有事务
    public void delete(Integer id) throws Exception{
        try {
            deptMapper.deleteById(id);  //根据部门id删除部门
            int i=1/0; //模拟抛出异常
            empMapper.deleteByDeptId(id);  //根据部门id删除部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();  //记录日志
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行解散部门的操作，此次解散的部门是"+id+"部门");
            deptLogMapper.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept listById(Integer id) {
        return deptMapper.listById(id);
    }

    @Override
    public void modify(Dept dept) {
        deptMapper.update(dept);
    }
}
