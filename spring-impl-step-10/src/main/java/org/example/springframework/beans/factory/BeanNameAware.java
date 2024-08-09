package org.example.springframework.beans.factory;

/**
 * 实现此接口，能感知到所属的 BeanName
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
