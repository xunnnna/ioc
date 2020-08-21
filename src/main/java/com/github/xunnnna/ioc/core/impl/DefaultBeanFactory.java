package com.github.xunnnna.ioc.core.impl;

import com.github.xunnnna.ioc.constant.enums.ScopeEnum;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.exception.IocRuntimeException;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.util.ClassUtils;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认工厂接口
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultBeanFactory implements BeanFactory {

    /**
     * bean信息 map
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * bean map
     */
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * bean类型map
     */
    private final Map<Class<?>, Set<String>> typeBeanNameMap = new ConcurrentHashMap<>();

    /**
     * 注册对象定义信息
     */
    protected void registerBeanDefinition(final String beanName, final BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
        // 注册类型beanName
        registerTypeBeanNames(beanDefinition);
        //3. 初始化 bean 信息
        final boolean lazyInit = beanDefinition.getLazyInit();
        if (!lazyInit) {
            this.registerSingletonBean(beanName, beanDefinition);
        }
    }

    private void registerTypeBeanNames(final BeanDefinition beanDefinition) {
        final Class<?> type = getType(beanDefinition);
        Set<String> beanNameSet = typeBeanNameMap.computeIfAbsent(type, k -> new HashSet<>());
        beanNameSet.add(beanDefinition.getName());
    }

    /**
     * 直接初始化的对象
     * （1）如果是 singleton & lazy-init=false 则进行初始化处理
     * （2）创建完成后，对象放入 {@link #beanMap} 中，便于后期使用
     * @param beanName
     * @param beanDefinition
     */
    private Object registerSingletonBean(final String beanName, final BeanDefinition beanDefinition) {
        // 单例的流程
        Object bean = beanMap.get(beanName);
        if (bean != null) {
            return bean;
        }
        Object newBean = createBean(beanDefinition);
        beanMap.put(beanName, newBean);
        return newBean;
    }

    private Class<?> getType(BeanDefinition beanDefinition) {
        String className = beanDefinition.getClassName();
        return ClassUtils.getClass(className);
    }

    @Override
    public Object getBean(final String beanName) {
        Assert.assertNotNull("beanName", beanName);
        //获取配置信息
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new IocRuntimeException("no such bean: " + beanName);
        }
        //如果为多例，创建新的
        if (ScopeEnum.PROTOTYPE.getCode().equals(beanDefinition.getScope())) {
            return this.createBean(beanDefinition);
        } else {
            return this.registerSingletonBean(beanName, beanDefinition);
        }
    }

    private Object createBean(final BeanDefinition beanDefinition) {
        String className = beanDefinition.getClassName();
        Class<?> clazz = ClassUtils.getClass(className);
        return ClassUtils.newInstance(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(final String beanName, final Class<T> requireType) {
        Assert.assertNotNull("beanName", beanName);
        Assert.assertNotNull("requireType", requireType);
        Object object = getBean(beanName);
        return (T)object;
    }

    @Override
    public boolean containsBean(String beanName) {
        Assert.assertNotNull("beanName", beanName);
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public boolean isTypeMatch(String beanName, Class<?> requireType) {
        Object bean = getBean(beanName);
        return bean.getClass().equals(requireType);
    }

    @Override
    public Class<?> getType(String beanName) {
        Assert.assertNotNull("beanName", beanName);
        Object bean = getBean(beanName);
        return bean.getClass();
    }

    /**
     * 根据类型获取对应的属性名称
     * @param requiredType 需求类型
     * @return bean 名称列表
     * @since 0.0.2
     */
    Set<String> getBeanNames(final Class<?> requiredType) {
        Assert.assertNotNull("requiredType", requiredType);
        return typeBeanNameMap.get(requiredType);
    }


}
