package com.hb.ssm.rest.activiti;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.task.Task;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.List;

/**
 * @description: 测试activiti
 *
 * 流程步骤是：部署流程》》》》》》启动流程》》》》》》查询用户id》》》》用户完成任务》》》流程结束
 *
 * @author: huangbo
 * @create: 2019-07-23 14:04
 **/

public class TestActiviti {
    //获取流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void creatTable(){
        ProcessEngine processEngine =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                        .buildProcessEngine();
    }

    /**
    * @Description:  发布流程
    * @Author: huangbo
    * @Date: 2019/7/23
    */
    @Test
    public void deploy() {
        //获取仓库服务的实例
        processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("activiti/bpmn/WorkTestNew.bpmn")
                .addClasspathResource("activiti/bpmn/WorkTestNew.png")
                .deploy();

        System.out.println("发布成功");
    }

    /**
    * @Description:  启动流程
    * @Author: huangbo
    * @Date: 2019/7/23
    */
    @Test
    public void startProcess() {
        //启动流程
        //使用流程定义的key启动流程实例，默认会按照最新版本启动流程实例
        ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceByKey("myProcess_1");

        System.out.println("pid: " + pi.getId() + ",activitiId: " + pi.getActivityId());
    }

    /**
    * @Description: 查看我的个人任务
    * @Author: huangbo
    * @Date: 2019/7/23
    */
    @Test
    public void queryMyTask() {
        //指定任务办理者
        String assignee = "老王";
        //查询任务的列表
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()  //创建任务查询对象
                .taskAssignee(assignee) //指定个人任务办理人
                .list();//返回list

        //遍历结合查看内容
        for (Task task : tasks) {
            System.out.println("taskId: " + task.getId() + ", taskName： " + task.getName());
            System.out.println("**************************************");

            System.out.println("taskId=" +"流程任务节点信息ID："+ task.getId());
            System.out.println("taskName:" +"流程任务节点名称ID：" +task.getName());
            System.out.println("assignee:" + "流程任务节点接受人："+task.getAssignee());
            System.out.println("createTime:" +"流程任务节点创建时间："+ task.getCreateTime());
            System.out.println("**************************************");
        }
    }

    /**
    * @Description:  完成我的个人任务
    * @Author: huangbo
    * @Date: 2019/7/23
    */
    @Test
    public void completeTask () {
        String taskId = "5002";
        //完成任务
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务");
    }

}