package org.burgers.aop.learning.common

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired

import static junit.framework.Assert.fail

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath:contexts/CommonContext.xml"])
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
        assert thing.actions.size() == 1
        assert thing.actions[0] == "doSomething"
    }

    @Test
    void doSomethingAfter(){
        def thing = new MyThing()
        myService.doSomethingAfter(thing)
        assert thing.actions.size() == 1
        assert thing.actions[0] == "doSomething"
    }

    @Test
    void doSomethingAround(){
        def thing = new MyThing()
        myService.doSomethingAround(thing)
        assert thing.actions.size() == 1
        assert thing.actions[0] == "doSomething"
    }

    @Test
    void doSomethingAfterThrowing(){
        def thing = new MyThing()
        try{
            myService.doSomethingAfterThrowing(thing)
            fail("Should have failed")
        }catch (e){
            assert e.class == KaboomException
        }
    }
}
