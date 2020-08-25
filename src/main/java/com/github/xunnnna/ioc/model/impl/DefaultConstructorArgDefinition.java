package com.github.xunnnna.ioc.model.impl;

import com.github.xunnnna.ioc.model.ConstructorArgDefinition;

/**
 * 构造器参数定义信息
 * Created by zhutingxuan on 2020/8/24.
 */
public class DefaultConstructorArgDefinition implements ConstructorArgDefinition {

    /**
     * 类型
     */
    private String type;

    /**
     * 参数值
     */
    private String value;

    /**
     * 引用名称
     */
    private String ref;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getRef() {
        return ref;
    }

    @Override
    public void setRef(String ref) {
        this.ref = ref;
    }
}
