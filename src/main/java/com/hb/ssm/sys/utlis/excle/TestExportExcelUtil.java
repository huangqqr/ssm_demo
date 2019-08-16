package com.hb.ssm.sys.utlis.excle;

import com.hb.ssm.rest.user.model.User;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试文件导出
 * @author liuyazhuang
 *
 */
public class TestExportExcelUtil {

    public static void main(String[] args) throws Exception{
        ExportExcelUtil<User> util = new ExportExcelUtil<User>();
        // 准备数据
        List<User> list = new ArrayList<>();
        list.add(new User(111,"张三asdf","男"));
        list.add(new User(111,"李四asd","男"));
        list.add(new User(111,"王五","女"));
        //for (int i = 0; i < 10; i++) {
        //    list.add(new User(111,"张三asdf","男"));
        //    list.add(new User(111,"李四asd","男"));
        //    list.add(new User(111,"王五","女"));
        //}
        String[] columnNames = { "ID", "姓名", "性别" };
        util.exportExcel("用户导出", columnNames, list, new FileOutputStream("C:/activiti/bpmn/test1.xlsx"), ExportExcelUtil.EXCEl_FILE_2007);
        //util.exportExcel("用户导出", columnNames, list, new FileOutputStream("C:/activiti/bpmn/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
    }
}