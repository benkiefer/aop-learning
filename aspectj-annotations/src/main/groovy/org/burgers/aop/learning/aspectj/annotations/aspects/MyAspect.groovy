package org.burgers.aop.learning.aspectj.annotations.aspects

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.JoinPoint
import org.springframework.stereotype.Component
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.burgers.aop.learning.common.KaboomException

@Aspect
@Component
class MyAspect {
//    defining the pointcut separately
    @Pointcut("execution(* org.burgers.aop.learning.common.MyService.doSomethingBefore(..))")
    void doSomethingBefore(){ }

//    using the pointcut defined above
    @Before("doSomethingBefore()")
    void doItBefore(JoinPoint joinPoint){
        joinPoint.getArgs()[0].actions << "before"
    }

//    creates the pointcut for you
    @After("execution(* org.burgers.aop.learning.common.MyService.doSomethingAfter(..))")
    void doItAfter(JoinPoint joinPoint){
        joinPoint.getArgs()[0].actions << "after"
    }

    @Around("execution(* org.burgers.aop.learning.common.MyService.doSomethingAround(..))")
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
