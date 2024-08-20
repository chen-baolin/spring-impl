package org.example.springframework.beans.factory;

import org.example.springframework.beans.BeansException;

/**
 * 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出相应的处理，如：执行自定义初始化，或者仅仅检查是否设置了所有强制属性。
 */
public interface InitializingBean {

    /**
     * Bean 填充属性之后调用
     * @throws BeansException
     */
    void afterPropertiesSet() throws Exception;
}
