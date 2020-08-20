package com.github.xunnnna.ioc.model;

/**
 * 对象定义属性
 * Created by zhutingxuan on 2020/8/20.
 */
public interface BeanDefinition {

    String getName();

    void setName(final String name);

    String getClassName();

    void setClassName(final String className);

}
