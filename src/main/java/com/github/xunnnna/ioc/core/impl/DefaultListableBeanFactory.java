package com.github.xunnnna.ioc.core.impl;

import com.github.xunnnna.ioc.core.ListableBeanFactory;
import com.github.xunnnna.ioc.exception.IocRuntimeException;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultListableBeanFactory extends DefaultBeanFactory implements ListableBeanFactory {

    @Override
    public <T> List<T> getBeans(Class<T> requireType) {
        Assert.assertNotNull("requireType", requireType);
        Set<String> beanNames = super.getBeanNames(requireType);
        if (CollectionUtils.isEmpty(beanNames)) {
            throw new IocRuntimeException("no such type: " + requireType);
        }
        return beanNames.stream().map(beanName -> super.getBean(beanName, requireType)).collect(Collectors.toList());
    }
}
