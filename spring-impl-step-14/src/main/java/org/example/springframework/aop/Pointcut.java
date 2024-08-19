package org.example.springframework.aop;

/**
 * 切入点
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
