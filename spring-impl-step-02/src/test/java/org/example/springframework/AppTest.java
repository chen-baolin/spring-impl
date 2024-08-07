package org.example.springframework;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.springframework.config.BeanDefinition;
import org.example.springframework.support.DefaultListableBeanFactory;
import org.example.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        userServiceSingleton.queryUserInfo();
        System.out.println(userServiceSingleton.equals(userService));
    }
}
