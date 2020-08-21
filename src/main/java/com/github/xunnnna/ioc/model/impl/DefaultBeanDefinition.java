package com.github.xunnnna.ioc.model.impl;

import com.github.xunnnna.ioc.model.BeanDefinition;

/**
 * 默认对象定义属性
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultBeanDefinition implements BeanDefinition {

    private String name;

    private String className;

    private String scope;

    private boolean lazyInit;

    /**
     * 初始化方法信息
     */
    private String initialize;

    /**
     * 销毁方法
     */
    private String destroy;

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

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean getLazyInit() {
        return lazyInit;
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    @Override
    public void setInitialize(String initialize) {
        this.initialize = initialize;
    }

    @Override
    public String getInitialize() {
        return initialize;
    }

    @Override
    public void setDestroy(String destroy) {
        this.destroy = destroy;
    }

    @Override
    public String getDestroy() {
        return destroy;
    }
}
