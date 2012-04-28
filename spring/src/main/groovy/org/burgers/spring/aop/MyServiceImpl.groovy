package org.burgers.spring.aop

import org.springframework.stereotype.Component

@Component
class MyServiceImpl implements MyService {

    void doSomething(MyThing myThing) {
        myThing.actions << "doSomething"
    }

    void doSomethingBefore(MyThing myThing) {
        doSomething(myThing)
    }

    void doSomethingAfter(MyThing myThing) {
        doSomething(myThing)
    }

    @Override
    void doSomethingAround(MyThing myThing) {
        doSomething(myThing)
    }

    @Override
    void doSomethingAfterThrowing(MyThing myThing) {
        doSomething(myThing)
        throw new KaboomException()
    }
}
