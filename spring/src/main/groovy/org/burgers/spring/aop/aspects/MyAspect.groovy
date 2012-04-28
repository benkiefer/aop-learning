package org.burgers.spring.aop.aspects

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.JoinPoint
import org.springframework.stereotype.Component
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.burgers.spring.aop.KaboomException

@Aspect
@Component
class MyAspect {
    @Pointcut("execution(* org.burgers.spring.aop.MyService.doSomethingBefore(..))")
    void doSomethingBefore(){ }

    @Before("doSomethingBefore()")
    void doItBefore(JoinPoint joinPoint){
        joinPoint.getArgs()[0].actions << "before"
    }

    @After("execution(* org.burgers.spring.aop.MyService.doSomethingAfter(..))")
    void doItAfter(JoinPoint joinPoint){
        joinPoint.getArgs()[0].actions << "after"
    }

    @Around("execution(* org.burgers.spring.aop.MyService.doSomethingAround(..))")
    void doItAround(ProceedingJoinPoint joinPoint){
        List<String> arg = joinPoint.getArgs()[0].actions
        arg << "before"
        joinPoint.proceed()
        arg << "after"
    }

    @AfterThrowing(pointcut = "execution(* *.*(..))", throwing = "error")
    void doAfterThrowing(JoinPoint joinPoint, KaboomException error){
        error.message = "Altered after throwing"
    }
}
