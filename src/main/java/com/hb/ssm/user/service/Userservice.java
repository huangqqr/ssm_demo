package com.hb.ssm.user.service;


import com.hb.ssm.user.model.User;

/**
 * @author huangbo on 2019/7/17
 */
public interface Userservice {
    User selectByPrimaryKey(Integer id);
}
