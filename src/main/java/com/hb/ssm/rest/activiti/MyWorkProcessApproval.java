package com.hb.ssm.rest.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.junit.Test;

/**
 * @description: 审核过程完成任务节点审批的类
 * @author: huangbo
 * @create: 2019-07-22 16:08
 **/

public class MyWorkProcessApproval {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void startProcessApproval(){
        TaskService taskService = processEngine.getTaskService();
        //taskId 就是查询任务中的 ID
        String taskId = "2504";
        //完成请假申请任务
        taskService.complete(taskId);
    }

}