package com.github.xunnnna.ioc.util;

import com.github.xunnnna.ioc.exception.IocRuntimeException;
import org.junit.Assert;

/**
 * Created by zhutingxuan on 2020/8/20.
 */
public final class ClassUtils {

    private ClassUtils(){}

    /**
     * 获取当前的 class loader
     * @return
     */
    public static ClassLoader currentClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类信息
     */
    public static Class<?> getClass(final String className) {
        Assert.assertNotNull("className", className);
        try {
            return currentClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new IocRuntimeException(e);
        }
    }

    public static Object newInstance(final Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IocRuntimeException(e);
        }

    }

}
