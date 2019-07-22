/**
 * @公司 *松的个人工作室
 * @用户: Administrator
 * @类命: UserController
 * @创建时间: 2018/11/17/017/ 11:08
 * @创建人:
 * @描叙:
 */
package com.hb.ssm.rest.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.ssm.sys.model.Result;
import com.hb.ssm.rest.user.mapper.UserMapper;
import com.hb.ssm.rest.user.model.User;
import com.hb.ssm.rest.user.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/11/17/017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

        @Resource
        private UserMapper userMapper;
        //@Resource
        @Autowired
        private Userservice userservice;

        @ResponseBody
        @RequestMapping(value = "/getById", method = RequestMethod.GET)
        public Result getByIdUser(Integer id){
            User user =  userservice.selectByPrimaryKey(id);
            return new Result(user);
        }
        @RequestMapping("/showUser.do")
        public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
           // long userId = Long.parseLong(request.getParameter("id"));
            User user = this.userMapper.selectByPrimaryKey(1);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(user));
            response.getWriter().close();
        }
}