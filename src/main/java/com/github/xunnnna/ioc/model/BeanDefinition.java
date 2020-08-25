package com.github.xunnnna.ioc.model;

import com.github.xunnnna.ioc.model.impl.DefaultConstructorArgDefinition;

import java.util.List;

/**
 * 对象定义属性
 * Created by zhutingxuan on 2020/8/20.
 */
public interface BeanDefinition {

    String getName();

    /**
     * 设置名称
     * @param name
     */
    void setName(final String name);

    String getClassName();

    /**
     * 设置类名
     * @param className
     */
    void setClassName(final String className);

    String getScope();

    /**
     * 设置是否单例
     * @param scope
     */
    void setScope(final String scope);

    boolean getLazyInit();

    /**
     * 设置是否为延迟加载
     * @param isLazyInit
     */
    void setLazyInit(final boolean isLazyInit);

    /**
     * 设置初始化方法
     * @param initialize 初始化方法名称
     */
    void setInitialize(final String initialize);

    /**
     * 获取初始化方法
     * @return 初始化方法
     */
    String getInitialize();

    /**
     * 设置销毁方法
     * @param destroy 销毁方法名称
     */
    void setDestroy(final String destroy);

    /**
     * 获取销毁方法
     * @return 销毁
     */
    String getDestroy();

    /**
     * 工厂类方法
     * @param factoryMethod 工厂类方法
     */
    void setFactoryMethod(final String factoryMethod);

    /**
     * 获取工厂类方法名称
     * @return 工厂类方法名称
     */
    String getFactoryMethod();

    /**
     * 构造器参数列表
     * @return 构造器参数列表
     */
    List<DefaultConstructorArgDefinition> getConstructorArgList();

    /**
     * 设置构造器参数定义列表
     * @param constructorArgList 构造器参数列表
     */
    void setConstructorArgList(final List<DefaultConstructorArgDefinition> constructorArgList);

}
