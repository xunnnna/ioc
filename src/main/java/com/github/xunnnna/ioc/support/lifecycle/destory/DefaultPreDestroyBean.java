package com.github.xunnnna.ioc.support.lifecycle.destory;

import com.github.xunnnna.ioc.exception.IocRuntimeException;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.support.lifecycle.DisposableBean;
import com.github.xunnnna.ioc.util.ClassUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 默认销毁对象
 *
 * 1. Methods annotated with {@link PreDestroy}
 * 2. destroy() as defined by the {@link com.github.xunnnna.ioc.support.lifecycle.DisposableBean} callback interface
 * 3. A custom configured destroy() method
 *
 * 针对注解（1）的处理，需要 class 信息结合 {@link BeanDefinition} 进行注解相关信息的拓展。
 *
 * [spring注解之@PreDestroy的实现原理](https://www.jianshu.com/p/70d18e65a1d5)
 *
 * Created by zhutingxuan on 2020/8/21.
 */
public class DefaultPreDestroyBean implements DisposableBean {

    /**
     * 对象实例
     */
    private final Object instance;

    /**
     * 对象属性定义
     */
    private final BeanDefinition beanDefinition;

    public DefaultPreDestroyBean(Object instance, BeanDefinition beanDefinition) {
        this.instance = instance;
        this.beanDefinition = beanDefinition;
    }

    @Override
    public void destroy() {
        preDestroy();

        disposableBean();

        customDestroy();
    }

    /**
     * 注解处理
     */
    private void preDestroy() {
        Optional<Method> methodOptional = ClassUtils.getMethodOptional(instance.getClass(), PreDestroy.class);
        methodOptional.ifPresent(method -> {
            //1. 信息校验
            Class<?>[] paramTypes = method.getParameterTypes();
            if(ArrayUtils.isNotEmpty(paramTypes)) {
                throw new IocRuntimeException("PreDestroy must be has no params.");
            }
            //2. 反射调用
            try {
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IocRuntimeException(e);
            }
        });
    }

    /**
     * 接口处理
     */
    private void disposableBean() {
        if(instance instanceof DisposableBean) {
            DisposableBean disposableBean = (DisposableBean)instance;
            disposableBean.destroy();
        }
    }

    /**
     * 自定义销毁函数
     */
    private void customDestroy() {
        String destroyName = beanDefinition.getDestroy();
        if(StringUtils.isNotEmpty(destroyName)) {
            try {
                Method method = instance.getClass().getMethod(destroyName);
                method.invoke(instance);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IocRuntimeException(e);
            }
        }
    }

}
