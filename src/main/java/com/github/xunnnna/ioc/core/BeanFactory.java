package com.github.xunnnna.ioc.core;

/**
 * 工厂接口
 * Created by zhutingxuan on 2020/8/20.
 */
public interface BeanFactory {

    /**
     * 根据名称获取实例对象
     * @param beanName
     * @return
     */
    Object getBean(final String beanName);

    /**
     * 根据名称和类型获取实例对象
     * @param beanName
     * @param requireType
     * @param <T>
     * @return
     */
    <T> T getBean(final String beanName, final Class<T> requireType);

    /**
     * 是否包含指定的 bean
     * @param beanName
     * @return
     */
    boolean containsBean(final String beanName);

    /**
     * 指定的 bean 和需要的类型二者是否匹配。
     * @param beanName
     * @param requireType
     * @return
     */
    boolean isTypeMatch(final String beanName, final Class<?> requireType);

    /**
     * 获取 bean 对应的类型
     * @param beanName
     * @return 类型信息
     */
    Class<?> getType(final String beanName);

}
