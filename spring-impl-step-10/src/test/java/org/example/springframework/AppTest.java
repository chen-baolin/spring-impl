package org.example.springframework;

import org.example.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.example.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.example.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.springframework.test.bean.UserService;
import org.example.springframework.test.common.MyBeanFactoryPostProcessor;
import org.example.springframework.test.common.MyBeanPostProcessor;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testPrototype(){
        // 1.创建上下文对象
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2.获取 Bean 对象调用方法
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);

        // 3.配置 scope="prototype/singleton"
        System.out.println(userService1);
        System.out.println(userService2);

        // 4. 打印十六进制哈希
        System.out.println(userService1 + " 十六进制哈希：" + Integer.toHexString(userService1.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService1).toPrintable());
    }

    @Test
    public void testXml() {
        // 1.创建上下文对象
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2.获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

    }
}
