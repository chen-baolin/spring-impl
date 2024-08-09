package org.example.springframework.context;

import org.example.springframework.beans.BeansException;
import org.example.springframework.beans.factory.Aware;

/**
 * 实现此接口，能感知到所属的 ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
