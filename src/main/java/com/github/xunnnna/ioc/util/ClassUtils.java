package com.github.xunnnna.ioc.util;

import com.github.xunnnna.ioc.exception.IocRuntimeException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

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

    /**
     * 获取指定注解的方法
     * @param tCLass
     * @param annotationClass
     * @return
     */
    public static Optional<Method> getMethodOptional(final Class<?> tCLass, final Class<? extends Annotation> annotationClass) {
        final Method[] methods = tCLass.getMethods();
        if (ArrayUtils.isEmpty(methods)) {
            return Optional.empty();
        }
        for(Method method : methods) {
            if(method.isAnnotationPresent(annotationClass)) {
                return Optional.of(method);
            }
        }
        return Optional.empty();
    }

}
