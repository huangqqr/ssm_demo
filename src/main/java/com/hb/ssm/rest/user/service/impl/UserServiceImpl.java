package com.hb.ssm.rest.user.service.impl;

import com.hb.ssm.rest.user.mapper.UserMapper;
import com.hb.ssm.rest.user.model.User;
import com.hb.ssm.rest.user.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户实现类
 * @author: huangbo
 * @create: 2019-07-17 13:59
 **/

@Service
public class UserServiceImpl implements Userservice {
    @Autowired
    private UserMapper mapper;

    @Override
    public User selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}