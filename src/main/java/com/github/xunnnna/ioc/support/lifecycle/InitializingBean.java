package com.github.xunnnna.ioc.support.lifecycle;

/**
 * Multiple lifecycle mechanisms configured for the same bean, with different initialization methods, are called as follows:
 *
 * 1. Methods annotated with @PostConstruct
 *
 * 2. initialize() as defined by the InitializingBean callback interface
 *
 * 3. A custom configured init() method
 *
 * Created by zhutingxuan on 2020/8/21.
 */
public interface InitializingBean {

    /**
     * 对象初始化完成之后调用
     */
    void initialize();

}
