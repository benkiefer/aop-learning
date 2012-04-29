package org.burgers.aop.learning.spring.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.springframework.stereotype.Component

@Component
class MyPerformanceAspect {
    void performance(ProceedingJoinPoint joinPoint){
        int startTime = System.currentTimeMillis()
        joinPoint.proceed()
        int endTime = System.currentTimeMillis()
        def seconds = (endTime - startTime) / 1000
        println "Method: ${joinPoint.signature.name} of class ${joinPoint.target.class.simpleName} took $seconds seconds"
    }
}
