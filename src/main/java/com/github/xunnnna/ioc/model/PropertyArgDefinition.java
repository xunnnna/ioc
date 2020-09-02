package com.github.xunnnna.ioc.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.xunnnna.ioc.model.impl.DefaultPropertyArgDefinition;

/**
 * Created by zhutingxuan on 2020/9/1.
 */
@JsonDeserialize(as = DefaultPropertyArgDefinition.class)
public interface PropertyArgDefinition {

    /**
     * 属性名称
     * @return 属性名称
     */
    String getName();

    /**
     * 设置属性名称
     * @param name 属性名称
     * @since 0.0.7
     */
    void setName(final String name);

    /**
     * 参数类型全称
     * @return 类型全称
     */
    String getType();

    /**
     * 设置参数类型全称
     * @param type 参数类型全称
     */
    void setType(final String type);

    /**
     * 值信息
     * @return 值信息
     */
    String getValue();

    /**
     * 设置值
     * @param value 值
     */
    void setValue(final String value);

    /**
     * 获取引用的名称
     * @return 引用的例子名称
     */
    String getRef();

    /**
     * 设置引用属性名称
     * @param ref 引用属性名称
     */
    void setRef(final String ref);
}
