package com.hb.ssm.sys.utlis.equals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import com.hb.ssm.rest.user.model.User;
import org.springframework.util.StringUtils;

/**
 * @description: 两个对象对比
 * @author: huangbo
 * @create: 2019-08-02 09:06
 **/
public class ReflectUtils {
    /**
     * Description: 获取修改内容  比较对象中全部的属性
     */
    public static String packageModifyContent(Object source, Object target) {
        StringBuffer modifyContent = new StringBuffer();
        if (null == source || null == target) {
            return "";
        }
        //取出source类
        Class<?> sourceClass = source.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field srcField : sourceFields) {
            String srcName = srcField.getName();
            //获取srcField值
            String srcValue = getFieldValue(source, srcName) == null ? "" : getFieldValue(source, srcName).toString();
            //获取对应的targetField值
            String targetValue = getFieldValue(target, srcName) == null ? "" : getFieldValue(target, srcName).toString();
            if (StringUtils.isEmpty(srcValue) && StringUtils.isEmpty(targetValue)) {
                continue;
            }
            if (!srcValue.equals(targetValue)) {
                modifyContent.append(srcName + "&" + targetValue + "&" + srcValue + ";");
            }

        }
        return modifyContent.toString();
    }

    /**
     * Description: 获取Obj对象的fieldName属性的值
     */
    private static Object getFieldValue(Object obj, String fieldName) {
        Object fieldValue = null;
        if (null == obj) {
            return null;
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (!methodName.startsWith("get")) {
                continue;
            }
            if (methodName.startsWith("get") && methodName.substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                try {
                    fieldValue = method.invoke(obj, new Object[]{});
                } catch (Exception e) {
                    System.out.println("取值出错，方法名 " + methodName);
                    continue;
                }
            }
        }
        return fieldValue;
    }


    /**
     * Description: 获取修改内容  比较对象部分属性
     */
    public static String packageModifyContent(Object source, Object target, Map<String,String> comparedProperties) {
        StringBuffer modifyContent = new StringBuffer();
        if(null == source || null == target) {
            return "";
        }
        //取出source类
        Class<?> sourceClass = source.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();
        for(Field srcField : sourceFields) {
            String srcName = srcField.getName();
            //获取此属性值。条件: 1 比较所有属性; 2 比较的属性在比较集合中
            if(null == comparedProperties || (null != comparedProperties && comparedProperties.containsKey(srcName))) {
                //获取srcField值
                String srcValue = getFieldValue(source, srcName) == null ? "" : getFieldValue(source, srcName).toString();
                //
                //获取对应的targetField值
                String targetValue = getFieldValue(target, srcName) == null ? "" : getFieldValue(target, srcName).toString();
                if(StringUtils.isEmpty(srcValue) && StringUtils.isEmpty(targetValue)) {
                    continue;
                }
                if(!srcValue.equals(targetValue)) {
                    modifyContent.append(comparedProperties.get(srcName) + "由‘" + targetValue + "’修改为‘" + srcValue + "’;");
                }
            }
        }
        return modifyContent.toString();
    }


    public static void main(String[] args){
        User user1 = new User();
        user1.setId(1);
        user1.setPassword("1111");
        user1.setUsername("张三");
        User user2 = new User();
        user2.setId(1);
        user2.setPassword("1234");
        user2.setUsername("李四");
        //username由‘李四’修改为‘张三’;password由‘1234’修改为‘1111’;
        String s = packageModifyContent(user1, user2);
        System.out.println(s);
    }
}