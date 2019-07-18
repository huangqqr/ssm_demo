package com.hb.ssm.user.service.impl;

import com.hb.ssm.user.mapper.UserMapper;
import com.hb.ssm.user.model.User;
import com.hb.ssm.user.service.Userservice;
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