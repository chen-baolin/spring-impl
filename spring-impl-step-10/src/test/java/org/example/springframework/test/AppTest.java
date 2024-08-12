package org.example.springframework.test;

import org.example.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1243663453L, "成功了！！！"));

        applicationContext.registerShutdownHook();
    }
}
