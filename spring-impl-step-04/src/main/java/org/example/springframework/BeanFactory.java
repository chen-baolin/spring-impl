package org.example.springframework;

public interface BeanFactory {

    Object getBean(String beanName) throws BeanException;

    Object getBean(String beanName, Object ... args) throws BeanException;
}
