/**
 * @公司 *松的个人工作室
 * @用户: Administrator
 * @类命: userMapper
 * @创建时间: 2018/11/17/017/ 10:47
 * @创建人:
 * @描叙:
 */
package com.hb.ssm.rest.user.mapper;


import com.hb.ssm.rest.user.model.User;

/**
 * Created by Administrator on 2018/11/17/017.
 */
public interface UserMapper {

    public User selectByPrimaryKey(Integer id);

}