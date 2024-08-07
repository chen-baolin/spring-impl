package org.example.springframework;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.springframework.config.BeanDefinition;
import org.example.springframework.config.BeanReference;
import org.example.springframework.support.DefaultListableBeanFactory;
import org.example.springframework.test.bean.UserDao;
import org.example.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
