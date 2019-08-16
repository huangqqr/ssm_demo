package com.hb.ssm.rest.demo.controller;

import com.hb.ssm.sys.utlis.upload.FileEntity;
import com.hb.ssm.sys.utlis.upload.FileUploadTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 上传文件
 * @author: huangbo
 * @create: 2019-08-02 10:37
 **/

@Controller
@RequestMapping("/uploadflv")
public class UploadController {
    @RequestMapping(value = "/upload.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                               HttpServletRequest request, ModelMap map) {
        String message = "";
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (entity != null) {
//                service.saveFile(entity);
                message = "上传成功";
                map.put("entity", entity);
                map.put("result", message);
            } else {
                message = "上传失败";
                map.put("result", message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("result", map);
    }
}