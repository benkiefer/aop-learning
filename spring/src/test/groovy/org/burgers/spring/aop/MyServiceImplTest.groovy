package org.burgers.spring.aop

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath:contexts/ApplicationContext.xml"])
class MyServiceImplTest {
    @Autowired
    MyService myService

    @Test
    void doSomething(){
        def thing = new MyThing()
        myService.doSomething(thing)
        assert thing.actions.size() == 1
        assert thing.actions.contains("doSomething")
    }
}
