package com.github.xunnnna.ioc.support.lifecycle.create;

import com.github.xunnnna.hell.support.tuple.impl.Pair;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;
import com.github.xunnnna.ioc.model.ConstructorArgDefinition;
import com.github.xunnnna.ioc.model.impl.DefaultConstructorArgDefinition;
import com.github.xunnnna.ioc.support.converter.StringValueConverterFactory;
import com.github.xunnnna.ioc.util.ClassUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;

/**
 * 根据构造器创建对象
 * 暂时不考虑线程安全问题
 * Created by zhutingxuan on 2020/8/24.
 */
public class ConstructorNewInstanceBean extends AbstractNewInstanceBean {

    private static final ConstructorNewInstanceBean INSTANCE = new ConstructorNewInstanceBean();

    /**
     * 获取单例实例
     * @return 单例
     */
    public static ConstructorNewInstanceBean getInstance() {
        return INSTANCE;
    }

    /**
     * 1. 获取所有的构造器列表 {@link Class#getConstructors()}
     * 2. 通过 {@link BeanDefinition} 指定的信息列表处理，选择精确匹配的构造器
     * 3. 反射调用构造器创建对象实例。{@link java.lang.reflect.Constructor#newInstance(Object...)}
     * 3.1 反射的时候注意构造器的访问级别。
     *
     * 这里有一个问题：
     * （1）我们每次处理 class 都需要重复处理 class 的反射信息，如何才能统一获取这些类的元信息，
     * 提供属性工具类?
     * （2）或者每次启动前，类似 spring，统一将所有的 class 元数据信息整理好。
     *
     *  如果这是一个普通系统，选择（1）比较好，延迟加载。
     *  如果这是一个实时系统，建议使用（2），初期启动比较慢，启动完成之后则比较快。
     *
     * @param beanFactory 对象工厂
     * @param beanDefinition 对象定义信息
     * @param beanClass 类信息
     * @return 结果
     */
    @Override
    protected Optional<Object> newInstanceOpt(BeanFactory beanFactory, BeanDefinition beanDefinition, Class<?> beanClass) {
        final List<ConstructorArgDefinition> argDefinitionList = beanDefinition.getConstructorArgList();
        //1. 无参
        if (CollectionUtils.isEmpty(argDefinitionList)) {
            return newInstanceOpt(beanClass);
        }
        //2. 根据指定参数信息获取对应构造器
        return newInstanceOpt(beanFactory, beanClass, argDefinitionList);
    }

    /**
     * 直接获取实例对象
     * @param beanClass 类信息
     * @return 实例结果
     */
    private Optional<Object> newInstanceOpt(final Class<?> beanClass) {
        Object instance = ClassUtils.newInstance(beanClass);
        return Optional.of(instance);
    }

    /**
     * 直接获取实例对象
     * @param beanFactory bean 工厂
     * @param beanClass 类信息
     * @param argDefinitionList 参数定义列表信息
     * @return 实例结果
     */
    private Optional<Object> newInstanceOpt(final BeanFactory beanFactory,
                                            final Class<?> beanClass,
                                            final List<ConstructorArgDefinition> argDefinitionList) {
        Pair<Class<?>[], Object[]> pair = getParamsPair(beanFactory, argDefinitionList);
        Constructor<?> constructor = ClassUtils.getConstructor(beanClass, pair.getValueOne());
        Object instance = ClassUtils.newInstance(constructor, pair.getValueTwo());
        return Optional.of(instance);
    }

    /**
     * 构建参数信息
     *
     * TODO: 引用类可能存在循环引用问题。如何判断是否存在循环依赖？
     *
     * 参考资料：
     * [Spring循环依赖检查算法分析](https://www.iteye.com/blog/jingzhongwen-2208921)
     *
     * http://ifeve.com/%E8%AE%BAspring%E4%B8%AD%E5%BE%AA%E7%8E%AF%E4%BE%9D%E8%B5%96%E7%9A%84%E6%AD%A3%E7%A1%AE%E6%80%A7%E4%B8%8Ebean%E6%B3%A8%E5%85%A5%E7%9A%84%E9%A1%BA%E5%BA%8F%E5%85%B3%E7%B3%BB/
     * @param beanFactory bean 工厂信息
     * @param argDefinitionList 参数列表
     * @return pair 信息
     */
    private Pair<Class<?>[], Object[]> getParamsPair(final BeanFactory beanFactory,
                                                  final List<ConstructorArgDefinition> argDefinitionList) {
        final int size = argDefinitionList.size();
        Class<?>[] types = new Class[size];
        Object[] values = new Object[size];

        for(int i = 0; i < size; i++) {
            ConstructorArgDefinition argDefinition = argDefinitionList.get(i);
            final String ref = argDefinition.getRef();
            // 引用类型
            if(StringUtils.isNotEmpty(ref)) {
                // 这里可以对常见的几个类型进行处理简化输入，后期考虑改。
                Class<?> refType = beanFactory.getType(ref);
                Object refValue = beanFactory.getBean(ref);

                types[i] = refType;
                values[i] = refValue;
            } else {
                // 指定值类型
                Class<?> valueType = ClassUtils.getClass(argDefinition.getType());
                final String valueStr = argDefinition.getValue();
                Object value = StringValueConverterFactory.convertValue(valueStr, valueType);

                types[i] = valueType;
                values[i] = value;
            }
        }
        return Pair.of(types, values);
    }

}
