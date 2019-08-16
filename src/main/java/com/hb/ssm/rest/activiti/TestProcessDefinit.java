package com.hb.ssm.rest.activiti;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.aspectj.weaver.NewMemberClassTypeMunger;
import org.junit.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @description: 测试流程定义
 * @author: huangbo
 * @create: 2019-07-25 08:52
 **/

public class TestProcessDefinit {
    //获取流程引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义 从(classpath)
     */
    @Test
    public void deploymentProcessDefinition_classpath() {
        //getRepositoryService() 是与流程定义和部署对象相关的service
        Deployment deploy = processEngine.getRepositoryService()
                //创建一个部署对象
                .createDeployment()
                //添加部署的名称
                .name("ClassPath发布流程定义")
                //从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("activiti/bpmn/a.bpmn")
                .addClasspathResource("activiti/bpmn/a.png")
                //完成部署
                .deploy();
        System.out.println("部署成功");
        System.out.println("部署ID: " + deploy.getId());
        System.out.println("部署名称: " + deploy.getName());

    }

    /**
     * 部署流程定义（从zip）
     */
    @Test
    public void deplomentProcessDefinition_zip() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("activiti/bpmn/a.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deploy = processEngine.getRepositoryService()
                .createDeployment()
                .name("zip定义流程发布")
                .addZipInputStream(zipInputStream)
                .deploy();

        System.out.println("部署成功");
        System.out.println("部署ID: " + deploy.getId());
        System.out.println("部署名称: " + deploy.getName());
    }

    /**
     * 查看流程定义
     */
    @Test
    public void findProcessDefinition() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                //创建一个流程定义的查询
                .createProcessDefinitionQuery()
                //使用部署对象的ID查询
                //.deploymentId(deploymentId)
                //使用流程定义的key查询
                //.processDefinitionKey(key)
                //使用流程定义的名称模糊查询
                //.processDefinitionNameLike(processDefintionName)
                /**排序*/
                .orderByProcessDefinitionVersion().asc()//按照版本的升序排列
//				.orderByProcessDefinitionName().desc()//按照流程定义的名称降序排列
                /**返回的结果集*/
                .list();
//               .singleResult();//返回惟一结果集
//				.count();//返回结果集数量
//				.listPage(firstResult, maxResults);//分页查询

        if(list!=null && list.size()>0){
            for(ProcessDefinition pd:list){
                System.out.println("流程定义ID:"+pd.getId());//流程定义的key+版本+随机生成数
                System.out.println("流程定义的名称:"+pd.getName());//对应helloworld.bpmn文件中的name属性值
                System.out.println("流程定义的key:"+pd.getKey());//对应helloworld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:"+pd.getVersion());//当流程定义的key值相同的相同下，版本升级，默认1
                System.out.println("资源名称bpmn文件:"+pd.getResourceName());
                System.out.println("资源名称png文件:"+pd.getDiagramResourceName());
                System.out.println("部署对象ID："+pd.getDeploymentId());
                System.out.println("#########################################################");
            }
        }
    }

    /**删除流程定义*/
    @Test
    public  void deleteProcessDefinition () {
        //使用部署id，完成删除
        String deploymentId = "1";
        /**级联删除
         *  不管流程是否启动，都能可以删除
         */
        processEngine.getRepositoryService()
                .deleteDeployment(deploymentId,true);

        System.out.println("删除成功");
    }

    /**查看流程圖*/
    @Test
    public void viewPic () throws IOException{
        /**将生成图片放到文件夹下*/
        String deploymentId = "10001";
        //获取图片资源名称
        List<String> list = processEngine.getRepositoryService()
                .getDeploymentResourceNames(deploymentId);
        //定义图片资源的名称
        String resourceName = "";
        if(list != null && list.size() > 0){
            for (String name : list) {
                if(name.indexOf("png") >= 0){
                    resourceName = name;
                }
            }
        }
        System.out.println(resourceName);
        //获取图片的输入流
        InputStream in = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, resourceName);

        //将图片生成搭配C盘的目录下
        File file = new File("C:/" + resourceName);

        FileUtils.copyInputStreamToFile(in,file);
    }

    /**附加功能，查询最新版本的流程定义*/
    @Test
    public void findLastVerstionProcessDefinition () {
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        /***
         * Map<String ,ProcessDefinition>
         * map集合的key，就是流程定义的key
         * map集合的value，就是流程定义的对象
         * map集合的特点：当map集合的key相同的情况下，后一次的值回替换前一次的值
         *
        */

        Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
        if(list != null && list.size() > 0){
            for(ProcessDefinition pd : list){
                map.put(pd.getKey(),pd);
            }
        }

        List<ProcessDefinition> pdList = new ArrayList<ProcessDefinition>(map.values());
        if( pdList != null && pdList.size() > 0){
            for(ProcessDefinition pd : pdList){
                System.out.println("流程定义ID ： " + pd.getId());
                System.out.println("流程定义的名称 ： " + pd.getName());
                System.out.println("流程定义的key ： " + pd.getKey());
                System.out.println("流程定义的版本 ： " + pd.getVersion());
                System.out.println("流程名称pbmn文件 ： " + pd.getResourceName());
                System.out.println("流程定义名称png文件 ： " + pd.getDiagramResourceName());
                System.out.println("流程对象的ID ： " + pd.getDeploymentId());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++=");
            }
        }
    }

    @Test
    public void deleteProcessDefinitionByKey () {
        //流程定义的key
        String processDefinitionKey = "a1";
        //先使用流程定义的key查询流程定义，查询出所有的版本
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .list();

        //遍历，获取每个流程定义的部署ID
        if(list != null && list.size() > 0){
            list.forEach( pd  ->{
                String deploymentId = pd.getDeploymentId();
                processEngine.getRepositoryService()
                        .deleteDeployment(deploymentId,true);
            });

        }
    }
}