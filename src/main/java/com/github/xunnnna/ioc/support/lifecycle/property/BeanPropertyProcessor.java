package com.github.xunnnna.ioc.support.lifecycle.property;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.PropertyArgDefinition;

import java.util.List;

/**
 * 对象属性设置类。
 *
 * 主要分为两个部分：
 *
 * （1）直接根据属性值设置
 * （2）根据引用属性设置
 * Created by zhutingxuan on 2020/9/1.
 */
public interface BeanPropertyProcessor {

    /**
     * 对象初始化完成之后调用
     * @param beanFactory 属性工厂
     * @param instance 对象实例
     * @param propertyArgList 参数信息列表
     */
    void propertyProcessor(final BeanFactory beanFactory,
                           final Object instance,
                           final List<PropertyArgDefinition> propertyArgList);
}
