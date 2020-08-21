package com.github.xunnnna.ioc.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xunnnna.ioc.core.impl.DefaultListableBeanFactory;
import com.github.xunnnna.ioc.exception.IocRuntimeException;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.model.impl.DefaultBeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by zhutingxuan on 2020/8/20.
 */
public class ClassPathJsonApplicationContext extends AbstractApplicationContext {

    private final String fileName;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClassPathJsonApplicationContext(String fileName) {
        this.fileName = fileName;
        super.init();
    }


    /**
     * 构建对象属性列表
     * @return
     */
    @Override
    protected List<? extends BeanDefinition> buildBeanDefinitionList() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        String json = getJson(is);
        List<DefaultBeanDefinition> list;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<DefaultBeanDefinition>>(){});
        } catch (JsonProcessingException e) {
            throw new IocRuntimeException(e);
        }
        return list;
    }


    private String getJson(InputStream inputStream) {
        try {
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IocRuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
