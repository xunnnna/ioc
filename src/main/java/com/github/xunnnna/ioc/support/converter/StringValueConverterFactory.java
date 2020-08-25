package com.github.xunnnna.ioc.support.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xunnnna.ioc.exception.IocRuntimeException;

/**
 * 字符串转换工厂类
 * Created by zhutingxuan on 2020/8/24.
 */
public final class StringValueConverterFactory {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 获取转换后的值
     * @param string 字符串
     * @param clazz 类型
     * @return 结果
     */
    public static Object convertValue(final String string, final Class<?> clazz) {
        try {
            return OBJECT_MAPPER.readValue(string, clazz);
        } catch (JsonProcessingException e) {
            throw new IocRuntimeException(e);
        }
    }

}
