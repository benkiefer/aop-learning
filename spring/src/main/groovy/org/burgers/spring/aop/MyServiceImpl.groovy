package org.burgers.spring.aop

import org.springframework.stereotype.Component

@Component
class MyServiceImpl implements MyService {

    void doSomething(MyThing myThing) {
        myThing.actions << "doSomething"
    }
}
