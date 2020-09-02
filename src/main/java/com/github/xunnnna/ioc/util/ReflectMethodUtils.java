package com.github.xunnnna.ioc.util;

import com.github.xunnnna.ioc.exception.IocRuntimeException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by zhutingxuan on 2020/8/24.
 */
public final class ReflectMethodUtils {

    private ReflectMethodUtils() {}

    /**
     * 执行反射调用
     * @param instance 对象实例，为空的时候针对 static 方法
     * @param method 方法实例
     * @param args 参数信息
     * @return 调用结果
     */
    public static Object invoke(final Object instance, final Method method, Object... args) {
        Assert.assertNotNull("method", method);
        try {
            return method.invoke(instance, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IocRuntimeException(e);
        }
    }

    /**
     * 直接执行调用无参方法
     *
     * 限制如下：
     * （1）工厂方法必须为静态
     * （2）工厂方法必须无参
     * （3）工厂方法必须返回指定对象信息
     * @param clazz 类信息
     * @param factoryMethod 工厂方法
     * @return 对象实例
     */
    public static Object invokeFactoryMethod(final Class<?> clazz, final Method factoryMethod) {
        Assert.assertNotNull("clazz", clazz);
        Assert.assertNotNull("factoryMethod", factoryMethod);

        //1. 信息校验
        final String methodName = factoryMethod.getName();
        //无参
        Class<?>[] parameterTypes = factoryMethod.getParameterTypes();
        if (ArrayUtils.isNotEmpty(parameterTypes)) {
            throw new IocRuntimeException(methodName + " must be has no params.");
        }
        //静态
        if (!Modifier.isStatic(factoryMethod.getModifiers())) {
            throw new IocRuntimeException(methodName + " must be static.");
        }
        //返回值
        Class<?> returnType = factoryMethod.getReturnType();
        if (!returnType.isAssignableFrom(clazz)) {
            throw new IocRuntimeException(methodName + " must be return " + returnType.getName());
        }

        //2. 调用
        return ReflectMethodUtils.invoke(null, factoryMethod);

    }

    /***
     * 调用 setter 方法，进行设置值
     */
    public static void invokeSetterMethod(final Object instance, final String propertyName, final Object value) {
        Assert.assertNotNull("instance", instance);
        Assert.assertNotNull("propertyName", propertyName);

        if (value == null) return;

        final Class<?> clazz = instance.getClass();
        String first = propertyName.substring(0, 1);
        String other = propertyName.substring(1);
        String setMethodName = "set" + StringUtils.upperCase(first) + other;

        final Class<?> paramType = value.getClass();

        try {
            Method method = clazz.getMethod(setMethodName, paramType);
            method.invoke(instance, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IocRuntimeException(e);
        }
    }



}
