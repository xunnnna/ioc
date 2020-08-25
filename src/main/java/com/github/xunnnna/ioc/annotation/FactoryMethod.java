package com.github.xunnnna.ioc.annotation;

import java.lang.annotation.*;

/**
 * 用于指定工厂方法的注解
 * Created by zhutingxuan on 2020/8/24.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FactoryMethod {
}
