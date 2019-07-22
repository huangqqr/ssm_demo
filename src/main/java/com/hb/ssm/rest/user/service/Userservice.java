package com.hb.ssm.rest.user.service;


import com.hb.ssm.rest.user.model.User;

/**
 * @author huangbo on 2019/7/17
 */
public interface Userservice {
    User selectByPrimaryKey(Integer id);
}
