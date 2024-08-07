package org.example.springframework.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.example.springframework.BeanException;
import org.example.springframework.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeanException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (constructor == null) {
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
