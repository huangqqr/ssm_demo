package com.hb.ssm.rest.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.junit.Test;

/**
 * @description: 工作流程启动流程类
 * @author: huangbo
 * @create: 2019-07-22 15:55
 **/

public class MyWorkStart {
    //启动流程
    @Test
    public void startProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        //流程的名称，对应流程定义表的key字段，也可以使用ByID来启动流程
        runtimeService.startProcessInstanceByKey("myProcess_1");
    }
}