package org.example.springframework.support;

import org.example.springframework.BeanException;
import org.example.springframework.BeanFactory;
import org.example.springframework.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeanException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeanException {
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;
}
