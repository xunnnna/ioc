package com.github.xunnnna.ioc.util;

import com.github.xunnnna.ioc.exception.IocRuntimeException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    /**
     * 根据类型和方法名获取方法
     * @param clazz 类型
     * @param name 方法名
     * @return
     */
    public static Method getMethodNoArgs(final Class<?> clazz, final String name) {
        try {
            return clazz.getMethod(name);
        } catch (NoSuchMethodException e) {
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

    /**
     * 获取构造器信息
     *
     * @param clazz      类
     * @param paramTypes 参数类型数组
     * @return 构造器
     */
    public static Constructor<?> getConstructor(final Class<?> clazz,
                                             final Class<?>... paramTypes) {
        Assert.assertNotNull("clazz", clazz);

        try {
            return clazz.getConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            throw new IocRuntimeException(e);
        }
    }

    /**
     * 根据构造器初始化对象实例
     * @param constructor 构造器
     * @param args 参数
     * @param <T> 泛型
     * @return 结果
     */
    public static <T> T newInstance(final Constructor<T> constructor,
                                    final Object... args) {
        Assert.assertNotNull("constructor", constructor);
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IocRuntimeException(e);
        }
    }

}
