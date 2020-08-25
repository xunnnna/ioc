package com.github.xunnnna.ioc.support.lifecycle.create;

import com.github.xunnnna.ioc.annotation.FactoryMethod;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.util.ClassUtils;
import com.github.xunnnna.ioc.util.ReflectMethodUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 根据静态方法创建对象实例
 * 暂时不考虑线程安全问题
 * Created by zhutingxuan on 2020/8/24.
 */
public class FactoryMethodNewInstanceBean extends AbstractNewInstanceBean {

    /**
     * 创建对象实例
     */
    private static final FactoryMethodNewInstanceBean INSTANCE = new FactoryMethodNewInstanceBean();

    /**
     * 获取对象实例
     */
    static FactoryMethodNewInstanceBean getInstance() {
        return INSTANCE;
    }

    @Override
    protected Optional<Object> newInstanceOpt(BeanFactory beanFactory, BeanDefinition beanDefinition, Class<?> beanClass) {
        // 指定了工厂方法
        if (StringUtils.isNotEmpty(beanDefinition.getFactoryMethod())) {
            return newInstance(beanClass, beanDefinition.getFactoryMethod());
        } else {
            // 通过注解
            return newInstance(beanClass);
        }
    }

    /**
     * 根据指定的方法名称创建对象信息
     * @param beanClass 对象信息
     * @param factoryMethodName 方法名称
     * @return 实例结果
     */
    private Optional<Object> newInstance(final Class<?> beanClass,
                                         final String factoryMethodName) {
        if (StringUtils.isNotEmpty(factoryMethodName)) {
            Method method = ClassUtils.getMethodNoArgs(beanClass, factoryMethodName);
            Object result = ReflectMethodUtils.invokeFactoryMethod(beanClass, method);
            return Optional.of(result);
        }
        return Optional.empty();
    }

    /**
     * 根据注解获取对应的信息
     * @param beanClass bean class 信息
     * @return 实例
     */
    private Optional<Object> newInstance(final Class<?> beanClass) {
        Optional<Method> methodOptional = ClassUtils.getMethodOptional(beanClass, FactoryMethod.class);
        return methodOptional.map(method -> ReflectMethodUtils.invokeFactoryMethod(beanClass, method));
    }


}
