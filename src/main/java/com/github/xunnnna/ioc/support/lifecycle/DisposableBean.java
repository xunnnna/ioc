package com.github.xunnnna.ioc.support.lifecycle;

/**
 * 对象被销毁之前调用
 *
 * Destroy methods are called in the same order:
 *
 * 1. Methods annotated with @PreDestroy
 *
 * 2. destroy() as defined by the DisposableBean callback interface
 *
 * 3. A custom configured destroy() method
 *
 * Created by zhutingxuan on 2020/8/21.
 */
public interface DisposableBean {

    /**
     * 销毁方法
     */
    void destroy();

}
