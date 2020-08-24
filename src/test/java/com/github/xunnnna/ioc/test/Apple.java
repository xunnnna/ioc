package com.github.xunnnna.ioc.test;

import com.github.xunnnna.ioc.support.lifecycle.DisposableBean;
import com.github.xunnnna.ioc.support.lifecycle.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by zhutingxuan on 2020/8/20.
 */
public class Apple implements InitializingBean, DisposableBean {

    public void eat() {
        System.out.println("delicious");
    }

    @Override
    public void destroy() {
        System.out.println("apple destroy");
    }

    @Override
    public void initialize() {
        System.out.println("apple initialize");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("apple postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("apple preDestroy");
    }
}
