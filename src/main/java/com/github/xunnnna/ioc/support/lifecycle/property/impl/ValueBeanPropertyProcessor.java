package com.github.xunnnna.ioc.support.lifecycle.property.impl;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.PropertyArgDefinition;
import com.github.xunnnna.ioc.support.converter.StringValueConverterFactory;
import com.github.xunnnna.ioc.support.lifecycle.property.SingleBeanPropertyProcessor;
import com.github.xunnnna.ioc.util.ClassUtils;
import com.github.xunnnna.ioc.util.ReflectMethodUtils;

/**
 * Created by zhutingxuan on 2020/9/1.
 */
public class ValueBeanPropertyProcessor implements SingleBeanPropertyProcessor {

    private static final ValueBeanPropertyProcessor INSTANCE = new ValueBeanPropertyProcessor();

    public static ValueBeanPropertyProcessor getInstance() {
        return INSTANCE;
    }

    @Override
    public void propertyProcessor(BeanFactory beanFactory, Object instance, PropertyArgDefinition propertyArgDefinition) {
        String valueStr = propertyArgDefinition.getValue();
        String typeStr = propertyArgDefinition.getType();

        Class<?> type = ClassUtils.getClass(typeStr);
//        Object value = StringValueConverterFactory.convertValue(valueStr, type);
        //todo: 字符串转换对象工厂
        ReflectMethodUtils.invokeSetterMethod(instance, propertyArgDefinition.getName(), valueStr);

    }
}
