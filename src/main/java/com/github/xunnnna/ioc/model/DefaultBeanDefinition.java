package com.github.xunnnna.ioc.model;

import com.github.xunnnna.ioc.constant.enums.ScopeEnum;

/**
 * 默认对象定义属性
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultBeanDefinition implements BeanDefinition {

    private String name;

    private String className;

    private String scope;

    private boolean lazyInit;

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

    public boolean getLazyInit() {
        return lazyInit;
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
}
