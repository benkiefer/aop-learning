package org.burgers.aop.learning.spring.aop

import java.lang.reflect.Method
import org.burgers.aop.learning.common.MyThing
import org.springframework.stereotype.Component
import org.springframework.aop.MethodBeforeAdvice
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.burgers.aop.learning.common.KaboomException

@Component
class MyAspect {

    void before(JoinPoint joinPoint) {
        joinPoint.args[0].actions << "before"
    }

    void after(JoinPoint joinPoint) {
        joinPoint.args[0].actions << "after"
    }

    void around(ProceedingJoinPoint joinPoint) {
        joinPoint.args[0].actions << "before"
        joinPoint.proceed()
        joinPoint.args[0].actions << "after"
    }

    void afterThrowing(JoinPoint joinPoint, KaboomException error){
        error.message = "Altered after throwing"
    }

}
