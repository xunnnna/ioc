package com.github.xunnnna.ioc.core.impl;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认工厂接口
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultBeanFactory implements BeanFactory {

    /**
     * 对象信息 map
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 对象 map
     */
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 注册对象定义信息
     */
    protected void registerBeanDefinition(final String beanName, final BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(final String beanName) {
        Object bean = beanMap.get(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Object newBean = createBean(beanDefinition);
        beanMap.put(beanName, newBean);
        return newBean;
    }

    @SuppressWarnings("rawtypes")
    private Object createBean(final BeanDefinition beanDefinition) {
        String className = beanDefinition.getClassName();
        Class clazz = ClassUtils.getClass(className);
        return ClassUtils.newInstance(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(final String beanName, final Class<T> clazz) {
        Object object = getBean(beanName);
        return (T)object;
    }
}
