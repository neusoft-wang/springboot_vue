package com.boylegu.springboot_vue.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.boylegu.springboot_vue.controller.StudentController.*(..) )")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("method->{}",request.getMethod());
        logger.info("ip ->{}",request.getRemoteAddr());
        logger.info("class_method->{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("agrs={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        System.out.println("2222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void afterReturning(Object object){
        logger.info("response= {}",object.toString());
    }
}
