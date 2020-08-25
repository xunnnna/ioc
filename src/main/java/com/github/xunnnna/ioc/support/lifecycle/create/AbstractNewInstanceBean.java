package com.github.xunnnna.ioc.support.lifecycle.create;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.support.lifecycle.NewInstanceBean;
import com.github.xunnnna.ioc.util.ClassUtils;
import org.junit.Assert;

import java.util.Optional;

/**
 * 抽象对象实例的实现
 * Created by zhutingxuan on 2020/8/24.
 */
public abstract class AbstractNewInstanceBean implements NewInstanceBean {

    /**
     * 创建新对象实例
     * @param beanFactory 对象工厂
     * @param beanClass 类信息
     * @param beanDefinition 对象定义信息
     * @return 实例 optional 信息
     */
    protected abstract Optional<Object> newInstanceOpt(final BeanFactory beanFactory,
                                                       final BeanDefinition beanDefinition,
                                                       final Class<?> beanClass);

    @Override
    public Object newInstance(BeanFactory beanFactory, BeanDefinition beanDefinition) {
        Assert.assertNotNull("beanFactory", beanFactory);
        Assert.assertNotNull("beanDefinition", beanDefinition);

        final Class<?> beanClass = ClassUtils.getClass(beanDefinition.getClassName());
        return newInstanceOpt(beanFactory, beanDefinition, beanClass).orElse(null);
    }
}
