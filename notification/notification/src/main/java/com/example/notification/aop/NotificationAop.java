package com.example.notification.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotificationAop {
    @Around("execution(* com.example.notification.service.*.addNotificationInternal(..))")
    public  Object around(ProceedingJoinPoint jp)throws  Throwable{

        System.out.println("started give notification method");

        try {
            Object result=jp.proceed();
            System.out.println("notification give successfully");
            return  result;
        }catch (Exception e){
            System.out.println("error happen"+ e.getMessage());
            throw  e;
        }finally {
            System.out.println("finished notification give method");
        }

    }

}
