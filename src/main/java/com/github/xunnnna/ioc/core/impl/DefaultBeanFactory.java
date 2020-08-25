package com.github.xunnnna.ioc.core.impl;

import com.github.xunnnna.hell.support.tuple.impl.Pair;
import com.github.xunnnna.ioc.constant.enums.ScopeEnum;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.exception.IocRuntimeException;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.support.lifecycle.DisposableBean;
import com.github.xunnnna.ioc.support.lifecycle.InitializingBean;
import com.github.xunnnna.ioc.support.lifecycle.create.DefaultNewInstanceBean;
import com.github.xunnnna.ioc.support.lifecycle.destory.DefaultPreDestroyBean;
import com.github.xunnnna.ioc.support.lifecycle.init.DefaultPostConstructBean;
import com.github.xunnnna.ioc.util.ClassUtils;
import org.junit.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认工厂接口
 * Created by zhutingxuan on 2020/8/20.
 */
public class DefaultBeanFactory implements BeanFactory, DisposableBean {

    /**
     * bean信息 map
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * bean map
     */
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 类型集合
     * （1）主要是为了 type 类型，获取对应的信息为做准备
     * （2）考虑到懒加载的处理。
     * @see #getBean(String, Class) 获取对应对象信息
     */
    private final Map<Class<?>, Set<String>> typeBeanNameMap = new ConcurrentHashMap<>();

    /**
     * 实例于 bean 定义信息 map
     */
    private final List<Pair<Object, BeanDefinition>> instanceBeanDefinitionList = new ArrayList<>();

    /**
     * 注册对象定义信息
     */
    protected void registerBeanDefinition(final String beanName, final BeanDefinition beanDefinition) {
        Assert.assertNotNull("beanName", beanName);
        Assert.assertNotNull("beanDefinition", beanDefinition);

        this.beanDefinitionMap.put(beanName, beanDefinition);
        // 注册类型beanName
        this.registerTypeBeanNames(beanDefinition);

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
    public Object  getBean(final String beanName) {
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

    /**
     * 根据对象定义信息创建对象
     * （1）注解 {@link javax.annotation.PostConstruct}
     * （2）添加 {@link com.github.xunnnna.ioc.support.lifecycle.InitializingBean} 初始化相关处理
     * （3）添加 {@link BeanDefinition#getInitialize()} 初始化相关处理
     *
     * TODO:
     * 1. 后期添加关于构造器信息的初始化
     * 2. 添加对应的 BeanPostProcessor
     *
     * 如果想使用注解相关信息，考虑实现 AnnotationBeanDefinition 统一处理注解信息。
     * 本期暂时忽略 (1)
     *
     * @param beanDefinition 对象定义信息
     * @return 创建的对象信息
     */
    private Object createBean(final BeanDefinition beanDefinition) {

        //1. 初始化相关处理
        Object instance = DefaultNewInstanceBean.getInstance()
                .newInstance(this, beanDefinition);
        //1.1 直接根据构造器
        //1.2 根据构造器，属性，静态方法
        //1.3 根据注解处理相关信息

        //2. 初始化完成之后的调用
        InitializingBean initializingBean = new DefaultPostConstructBean(instance, beanDefinition);
        initializingBean.initialize();
        //2.1 将初始化的信息加入列表中，便于后期销毁使用
        Pair<Object, BeanDefinition> pair = Pair.of(instance, beanDefinition);
        instanceBeanDefinitionList.add(pair);

        return instance;
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
     */
    Set<String> getBeanNames(final Class<?> requiredType) {
        Assert.assertNotNull("requiredType", requiredType);
        return typeBeanNameMap.get(requiredType);
    }

    @Override
    public void destroy() {
        // 销毁所有的属性信息
        System.out.println("destroy all beans start");
        for(Pair<Object, BeanDefinition> entry : instanceBeanDefinitionList) {
            DisposableBean disposableBean = new DefaultPreDestroyBean(entry.getValueOne(), entry.getValueTwo());
            disposableBean.destroy();
        }
        System.out.println("destroy all beans end");
    }

}
