package com.github.xunnnna.ioc.core;

/**
 * 工厂接口
 * Created by zhutingxuan on 2020/8/20.
 */
public interface BeanFactory {

    Object getBean(final String beanName);

    <T> T getBean(final String beanName, final Class<T> clazz);

}
