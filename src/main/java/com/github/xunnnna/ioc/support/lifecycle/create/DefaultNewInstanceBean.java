package com.github.xunnnna.ioc.support.lifecycle.create;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.support.lifecycle.NewInstanceBean;

/**
 * 默认新建对象实例的实现
 * 暂不不考虑线程安全问题
 * Created by zhutingxuan on 2020/8/24.
 */
public class DefaultNewInstanceBean implements NewInstanceBean {

    /**
     * 单例
     */
    private static final DefaultNewInstanceBean INSTANCE = new DefaultNewInstanceBean();

    /**
     * 获取实例
     * @return 实例
     */
    public static DefaultNewInstanceBean getInstance() {
        return INSTANCE;
    }

    @Override
    public Object newInstance(BeanFactory beanFactory, BeanDefinition beanDefinition) {
        //1. 工厂方法
        Object instance = FactoryMethodNewInstanceBean.getInstance().newInstance(beanFactory, beanDefinition);
        if (instance == null) {
            //2. 构造器
            instance = ConstructorNewInstanceBean.getInstance().newInstance(beanFactory, beanDefinition);
        }
        return instance;
    }
}
