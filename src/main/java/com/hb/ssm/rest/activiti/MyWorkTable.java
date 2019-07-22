package com.hb.ssm.rest.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * @description: 工作流程测试
 * 创建24张数据表
 * 流程步骤是：部署流程》》》》》》启动流程》》》》》》查询用户id》》》》用户完成任务》》》流程结束
 * @author: huangbo
 * @create: 2019-07-22 15:40
 **/

public class MyWorkTable {
    @Test
    public void creatTable(){
        ProcessEngine processEngine =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                        .buildProcessEngine();
    }
}