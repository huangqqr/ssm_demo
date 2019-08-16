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
import com.hb.ssm.sys.utlis.excle.ExportExcelUtil;
import com.hb.ssm.sys.utlis.excle.ExportExcelWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /** 
    * @Description: 测试在网页上点击按钮导出excel
    * @Param: [id] 
    * @return: com.hb.ssm.sys.model.Result 
    * @Author: huangbo
    * @Date: 2019/8/2 
    */
    @RequestMapping("/get/excel")
    public void getExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 准备数据
        List<User> list = new ArrayList<>();
        list = userMapper.selectAll();
        //for (int i = 0; i < 10; i++) {
        //    list.add(new User(111,"张三asdf","男"));
        //    list.add(new User(111,"李四asd","男"));
        //    list.add(new User(111,"王五","女"));
        //}
        String[] columnNames = { "ID", "姓名", " 性别"};
        String fileName = "excel1";
        ExportExcelWrapper<User> util = new ExportExcelWrapper<User>();
        util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelUtil.EXCEL_FILE_2003);
    }


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