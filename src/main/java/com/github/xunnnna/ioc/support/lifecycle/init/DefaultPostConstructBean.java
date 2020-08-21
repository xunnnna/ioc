package com.github.xunnnna.ioc.support.lifecycle.init;

import com.github.xunnnna.ioc.exception.IocRuntimeException;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.support.lifecycle.InitializingBean;
import com.github.xunnnna.ioc.util.ClassUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 默认创建对象初始化
 * <p> project: ioc-DefaultCreateBeanInit </p>
 * <p> create on 2019/11/8 21:37 </p>
 *
 * （1）注解 {@link PostConstruct}
 * （2）添加 {@link com.github.xunnnna.ioc.support.lifecycle.InitializingBean} 初始化相关处理
 * （3）添加 {@link BeanDefinition#getInitialize()} 初始化相关处理
 *
 * 针对注解（1）的处理，需要 class 信息结合 {@link BeanDefinition} 进行注解相关信息的拓展。
 *
 * Created by zhutingxuan on 2020/8/21.
 */
public class DefaultPostConstructBean implements InitializingBean {

    /**
     * 对象实例
     */
    private final Object instance;

    /**
     * 对象属性定义
     */
    private final BeanDefinition beanDefinition;

    public DefaultPostConstructBean(Object instance, BeanDefinition beanDefinition) {
        this.instance = instance;
        this.beanDefinition = beanDefinition;
    }

    @Override
    public void initialize() {

        postConstruct();

        initializingBean();

        customInit();
    }

    /**
     * 注解处理
     */
    private void postConstruct() {
        Optional<Method> methodOptional = ClassUtils.getMethodOptional(instance.getClass(), PostConstruct.class);

        methodOptional.ifPresent(method -> {
            //1. 信息校验
            Class<?>[] paramTypes = method.getParameterTypes();
            if(ArrayUtils.isNotEmpty(paramTypes)) {
                throw new IocRuntimeException("PostConstruct must be has no params.");
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
    private void initializingBean() {
        if(instance instanceof InitializingBean) {
            InitializingBean initializingBean = (InitializingBean)instance;
            initializingBean.initialize();
        }
    }

    /**
     * 自定义初始化函数
     */
    private void customInit() {
        String initName = beanDefinition.getInitialize();
        if (StringUtils.isNotEmpty(initName)) {
            try {
                Method method = instance.getClass().getMethod(initName);
                method.invoke(instance);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IocRuntimeException(e);
            }
        }
    }

}
