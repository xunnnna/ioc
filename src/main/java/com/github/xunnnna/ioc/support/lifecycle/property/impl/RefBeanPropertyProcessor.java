package com.github.xunnnna.ioc.support.lifecycle.property.impl;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.PropertyArgDefinition;
import com.github.xunnnna.ioc.support.lifecycle.property.SingleBeanPropertyProcessor;
import com.github.xunnnna.ioc.util.ReflectMethodUtils;

/**
 * Created by zhutingxuan on 2020/9/1.
 */
public class RefBeanPropertyProcessor implements SingleBeanPropertyProcessor {

    private static final RefBeanPropertyProcessor INSTANCE = new RefBeanPropertyProcessor();

    public static RefBeanPropertyProcessor getInstance() {
        return INSTANCE;
    }

    @Override
    public void propertyProcessor(BeanFactory beanFactory, Object instance, PropertyArgDefinition propertyArgDefinition) {
        String ref = propertyArgDefinition.getRef();
        Object bean = beanFactory.getBean(ref);
        ReflectMethodUtils.invokeSetterMethod(instance, propertyArgDefinition.getName(), bean);

    }
}
