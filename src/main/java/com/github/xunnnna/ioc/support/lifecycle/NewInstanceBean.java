package com.github.xunnnna.ioc.support.lifecycle;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;

/**
 * 创建一个对象，有四种方式
 *
 * （1）直接使用 {@link BeanDefinition#getFactoryMethod()} 创建对象
 * （2）根据构造器创建对象
 *  2.1 默认无参构造器
 *  2.2 通过指定的构造器信息创建
 * Created by zhutingxuan on 2020/8/24.
 */
public interface NewInstanceBean {

    /**
     * 对象初始化完成之后调用
     * @param beanFactory 属性工厂
     * @param beanDefinition 对象基本信息定义
     * @return 对象实例
     */
    Object newInstance(final BeanFactory beanFactory, final BeanDefinition beanDefinition);
}
