package com.hb.ssm.rest.dept.service.impl;

import com.hb.ssm.rest.dept.mapper.DeptMapper;
import com.hb.ssm.rest.dept.model.Dept;
import com.hb.ssm.rest.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户实现类
 * @author: huangbo
 * @create: 2019-07-17 13:59
 **/

@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper mapper;


    @Override
    public Dept selectById(Integer id) {
        return mapper.selectById(id);
    }
}