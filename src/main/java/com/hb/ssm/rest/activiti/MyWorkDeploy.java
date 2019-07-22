package com.hb.ssm.rest.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

/**
 * @description: 工作流程发布
 * @author: huangbo
 * @create: 2019-07-22 15:46
 **/

public class MyWorkDeploy {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程
    @Test
    public void deployProcess(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("activiti/bpmn/WorkTestNew.bpmn");//bpmn文件的名称
        builder.deploy();
    }
}