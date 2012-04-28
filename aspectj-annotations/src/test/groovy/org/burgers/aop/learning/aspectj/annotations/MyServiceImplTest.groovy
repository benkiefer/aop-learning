package org.burgers.aop.learning.aspectj.annotations

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test

import static junit.framework.Assert.fail
import org.burgers.aop.learning.common.MyThing
import org.burgers.aop.learning.common.MyService

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath:contexts/AnnotationContext.xml"])
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

    @Test
    void doSomethingBefore(){
        def thing = new MyThing()
        myService.doSomethingBefore(thing)
        assert thing.actions.size() == 2
        assert thing.actions[0] == "before"
        assert thing.actions[1] == "doSomething"
    }

    @Test
    void doSomethingAfter(){
        def thing = new MyThing()
        myService.doSomethingAfter(thing)
        assert thing.actions.size() == 2
        assert thing.actions[0] == "doSomething"
        assert thing.actions[1] == "after"
    }

    @Test
    void doSomethingAround(){
        def thing = new MyThing()
        myService.doSomethingAround(thing)
        assert thing.actions.size() == 3
        assert thing.actions[0] == "before"
        assert thing.actions[1] == "doSomething"
        assert thing.actions[2] == "after"
    }

    @Test
    void doSomethingAfterThrowing(){
        def thing = new MyThing()
        try{
            myService.doSomethingAfterThrowing(thing)
            fail("Should have failed")
        }catch (e){
            assert e.message == "Altered after throwing"
        }

    }
}
