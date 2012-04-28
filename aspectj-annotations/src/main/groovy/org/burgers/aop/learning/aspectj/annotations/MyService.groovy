package org.burgers.aop.learning.aspectj.annotations

interface MyService {
    void doSomething(MyThing myThing)
    void doSomethingBefore(MyThing myThing)
    void doSomethingAfter(MyThing myThing)
    void doSomethingAround(MyThing myThing)
    void doSomethingAfterThrowing(MyThing myThing)
}
