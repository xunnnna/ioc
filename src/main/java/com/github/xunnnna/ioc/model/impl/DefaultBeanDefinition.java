package com.github.xunnnna.ioc.model.impl;

import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.model.ConstructorArgDefinition;
import com.github.xunnnna.ioc.model.PropertyArgDefinition;

import java.util.List;

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

    /**
     * 工厂方法
     */
    private String factoryMethod;

    /**
     * 构造器参数
     */
    private List<ConstructorArgDefinition> constructorArgList;

    /**
     * 属性参数列表
     * @since 0.0.7
     */
    private List<PropertyArgDefinition> propertyArgList;

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

    @Override
    public void setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    @Override
    public String getFactoryMethod() {
        return factoryMethod;
    }

    @Override
    public List<ConstructorArgDefinition> getConstructorArgList() {
        return constructorArgList;
    }

    @Override
    public void setConstructorArgList(List<ConstructorArgDefinition> constructorArgList) {
        this.constructorArgList = constructorArgList;
    }

    @Override
    public List<PropertyArgDefinition> getPropertyArgList() {
        return propertyArgList;
    }

    @Override
    public void setPropertyArgList(List<PropertyArgDefinition> propertyArgList) {
        this.propertyArgList = propertyArgList;
    }


}
