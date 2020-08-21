package com.github.xunnnna.ioc.constant.enums;

/**
 * Created by zhutingxuan on 2020/8/21.
 */
public enum ScopeEnum {

    /**
     * 单例
     */
    SINGLETON("singleton"),

    /**
     * 多例
     */
    PROTOTYPE("prototype");

    private final String code;

    ScopeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
