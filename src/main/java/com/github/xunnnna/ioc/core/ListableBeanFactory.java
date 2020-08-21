package com.github.xunnnna.ioc.core;

import java.util.List;

/**
 * Created by zhutingxuan on 2020/8/20.
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 获取制定类型
     * @param requireType 类型
     * @param <T> 范型
     * @return list of bean
     */
    <T> List<T> getBeans(final Class<T> requireType);

}
