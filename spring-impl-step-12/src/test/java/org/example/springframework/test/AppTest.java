package org.example.springframework.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.example.springframework.aop.AdvisedSupport;
import org.example.springframework.aop.MethodMatcher;
import org.example.springframework.aop.TargetSource;
import org.example.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.example.springframework.aop.framework.Cglib2AopProxy;
import org.example.springframework.aop.framework.JdkDynamicAopProxy;
import org.example.springframework.aop.framework.ReflectiveMethodInvocation;
import org.example.springframework.test.bean.IUserService;
import org.example.springframework.test.bean.UserService;
import org.example.springframework.test.bean.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testDynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.example.springframework.test.bean.IUserService.*(..))"));

        // jdk代理对象
        IUserService jdkProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + jdkProxy.queryUserInfo());
        System.out.println();
        // cglib代理对象
        IUserService cglibProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + cglibProxy.register("luffy"));
    }

    @Test
    public void testProxyMethod() {
        // 目标对象
        Object targetObj = new UserService();
        // AOP  代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("");
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
    }

    @Test
    public void testMatches() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.example.springframework.test.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }
}
