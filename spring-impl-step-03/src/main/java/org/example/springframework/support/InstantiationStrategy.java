package org.example.springframework.support;

import org.example.springframework.BeanException;
import org.example.springframework.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeanException;
}
