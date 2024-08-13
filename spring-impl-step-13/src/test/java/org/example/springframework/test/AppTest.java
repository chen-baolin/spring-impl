package org.example.springframework.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.example.springframework.aop.AdvisedSupport;
import org.example.springframework.aop.ClassFilter;
import org.example.springframework.aop.MethodMatcher;
import org.example.springframework.aop.TargetSource;
import org.example.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.example.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.example.springframework.aop.framework.Cglib2AopProxy;
import org.example.springframework.aop.framework.JdkDynamicAopProxy;
import org.example.springframework.aop.framework.ProxyFactory;
import org.example.springframework.aop.framework.ReflectiveMethodInvocation;
import org.example.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.example.springframework.context.support.ClassPathXmlApplicationContext;
import org.example.springframework.test.bean.IUserService;
import org.example.springframework.test.bean.UserService;
import org.example.springframework.test.bean.UserServiceBeforeAdvice;
import org.example.springframework.test.bean.UserServiceInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.example.springframework.test.bean.IUserService.*(..))"));
    }

    @Test
    public void testProxyFactory() {
        advisedSupport.setProxyTargetClass(false); // false/true，JDK动态代理、CGlib动态代理
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void testBeforeAdvice() {
        UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
        advisedSupport.setMethodInterceptor(interceptor);

        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy.queryUserInfo());
    }

    @Test
    public void testAdvisor() {
        // 目标对象
        IUserService userService = new UserService();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* org.example.springframework.test.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(userService);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true); // false/true，JDK动态代理、CGlib动态代理

            IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
            System.out.println("测试结果：" + proxy.queryUserInfo());
        }
    }

    @Test
    public void testAop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
