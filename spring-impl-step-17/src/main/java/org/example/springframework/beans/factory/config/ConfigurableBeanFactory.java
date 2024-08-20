package org.example.springframework.beans.factory.config;

import org.example.springframework.beans.factory.HierarchicalBeanFactory;
import org.example.springframework.core.convert.ConversionService;
import org.example.springframework.util.StringValueResolver;
import org.jetbrains.annotations.Nullable;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    @Nullable
    ConversionService getConversionService();
}
