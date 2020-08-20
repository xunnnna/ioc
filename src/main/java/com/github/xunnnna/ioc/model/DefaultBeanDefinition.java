package com.github.xunnnna.ioc.model;

/**
 * 默认对象定义属性
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultBeanDefinition implements BeanDefinition {

    private String name;

    private String className;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }
}
