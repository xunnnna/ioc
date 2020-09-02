package com.github.xunnnna.ioc.model.impl;

import com.github.xunnnna.ioc.model.PropertyArgDefinition;

/**
 * Created by zhutingxuan on 2020/9/1.
 */
public class DefaultPropertyArgDefinition implements PropertyArgDefinition {

    /**
     * 属性名称
     */
    private String name;

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "DefaultPropertyArgDefinition{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
