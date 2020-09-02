package com.github.xunnnna.ioc.support.lifecycle.property.impl;

import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.PropertyArgDefinition;
import com.github.xunnnna.ioc.support.lifecycle.property.BeanPropertyProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 暂不考虑线程安全问题
 * Created by zhutingxuan on 2020/9/1.
 */
public class DefaultBeanPropertyProcessor implements BeanPropertyProcessor {

    private static final DefaultBeanPropertyProcessor INSTANCE = new DefaultBeanPropertyProcessor();

    public static DefaultBeanPropertyProcessor getInstance() {
        return INSTANCE;
    }


    @Override
    public void propertyProcessor(BeanFactory beanFactory, Object instance, List<PropertyArgDefinition> propertyArgList) {
        if (CollectionUtils.isEmpty(propertyArgList) || instance == null) return;

        for (PropertyArgDefinition propertyArgDefinition : propertyArgList) {
            if (propertyArgDefinition == null) continue;;
            String ref = propertyArgDefinition.getRef();
            if(StringUtils.isEmpty(ref)) {
                ValueBeanPropertyProcessor.getInstance()
                        .propertyProcessor(beanFactory, instance, propertyArgDefinition);
            } else {
                RefBeanPropertyProcessor.getInstance()
                        .propertyProcessor(beanFactory, instance, propertyArgDefinition);
            }

        }


    }
}
