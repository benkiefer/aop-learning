package org.burgers.aop.learning.aspectj.annotations.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.springframework.stereotype.Component
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
@Component
class MyPerformanceAspect {

    @Around("execution(* org.burgers.aop.learning.common.MyService.*(..))")
    void performance(ProceedingJoinPoint joinPoint){
        int startTime = System.currentTimeMillis()
        joinPoint.proceed()
        int endTime = System.currentTimeMillis()
        def seconds = (endTime - startTime) / 1000
        println "Method: ${joinPoint.signature.name} of class ${joinPoint.target.class.simpleName} took $seconds seconds"
    }
}
