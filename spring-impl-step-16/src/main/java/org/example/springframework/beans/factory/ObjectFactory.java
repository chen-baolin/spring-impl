package org.example.springframework.beans.factory;

import org.example.springframework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
