package org.example.springframework.beans.factory;

import org.example.springframework.beans.BeansException;

/**
 * 实现此接口，能感知到所属的 ClassLoader
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
