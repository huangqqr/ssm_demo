package com.hb.ssm.sys.config.aop;

import com.hb.ssm.sys.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertFalse;

/**
 * @description: 面向切面编程，接口校验
 * @author: huangbo
 * @create: 2019-07-17 16:40
 **/
//@Component
@Aspect
public class ApiAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * signature
     */
    private static final String SIGNATURE = "90FA06DF546D28D7504E7F5C2246AE2A";

    /**
     * 默认构造函数
     */
    public ApiAspect() {
        System.out.println("初始化切面");
    }

    /**
     * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点,第一种配置切入点的方法
     */
    @Pointcut("execution(* com.hb.ssm.rest.*.controller.*.*(..))")
    public void checkRequest() {
    }

    /**
     * aop前置通知
     * @Author: huangbo
     * @Date: 2019/7/18
     */
    @Before("checkRequest()")
    private void before(JoinPoint joinPoint) {

    }

    /**
    * @Description:  aop后置通知
    * @Author: huangbo
    * @Date: 2019/7/18
    */
    @After("checkRequest()")
    private void after(JoinPoint joinPoint) {
    }

    /**
     * @Description: aop环绕通知
     * @Author: huangbo
     * @Date: 2019/7/18
     */
    //@Around("checkRequest()")
    //private Object around(JoinPoint joinPoint) throws Throwable {
    //    ProceedingJoinPoint tempJoinPoint = (ProceedingJoinPoint) joinPoint;
    //    //Object object = tempJoinPoint.proceed();
    //    Result result = null;
    //    //try {
    //    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    //    //String signature = request.getHeader("signature");
    //    String signature = request.getParameter("signature");
    //    logger.info("request header 的 signature 参数" + signature);
    //
    //    if (StringUtils.isNotBlank(signature) && SIGNATURE.equals(signature)) {
    //        result = (Result) tempJoinPoint.proceed();
    //        return result;
    //    } else {
    //        return new Result("签名不正确，或没有签名",false);
    //    }
    //}
}