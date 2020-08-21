package com.github.xunnnna.ioc.model;

import com.github.xunnnna.ioc.constant.enums.ScopeEnum;

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

}
