package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装实体类，对返回的记录数和数据列表list进行封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total; //总记录条数
    private List<Emp> rows; //当前页结果列表
}
