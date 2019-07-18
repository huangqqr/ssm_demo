/**
 * @公司 *松的个人工作室
 * @用户: Administrator
 * @类命: UserController
 * @创建时间: 2018/11/17/017/ 11:08
 * @创建人:
 * @描叙:
 */
package com.hb.ssm.rest.dept.controller;

import com.hb.ssm.rest.dept.model.Dept;
import com.hb.ssm.rest.dept.service.DeptService;
import com.hb.ssm.sys.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/11/17/017.
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

        @Autowired
        private DeptService deptService;

        @ResponseBody
        @RequestMapping(value = "/getById", method = RequestMethod.GET)
        public Result getByIdUser(Integer id){
            Dept dept =  deptService.selectById(id);
            return new Result(dept);
        }
}