package com.hb.ssm.rest.dept.service;


import com.hb.ssm.rest.dept.model.Dept;

/**
 * @author huangbo on 2019/7/17
 */
public interface DeptService {
    Dept selectById(Integer id);
}
