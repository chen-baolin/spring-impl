package org.example.springframework.beans.factory;

import org.example.springframework.beans.BeansException;

public interface DisposableBean {

    /**
     * Bean 销毁时调用
     * @throws BeansException
     */
    void destroy() throws Exception;
}
