package com.github.xunnnna.ioc.context;

import com.github.xunnnna.ioc.constant.enums.ScopeEnum;
import com.github.xunnnna.ioc.core.impl.DefaultListableBeanFactory;
import com.github.xunnnna.ioc.model.BeanDefinition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by zhutingxuan on 2020/8/21.
 */
public abstract class AbstractApplicationContext extends DefaultListableBeanFactory implements ApplicationContext {

    @Override
    public String getApplicationName() {
        return "application context";
    }

    protected void init() {
        List<? extends BeanDefinition> beanDefinitions = buildBeanDefinitionList();

        this.registerBeanDefinitions(beanDefinitions);

        this.registerShutdownHook();
    }

    /**
     * 构建 bean 属性定义列表
     * @return 属性定义列表
     */
    protected abstract List<? extends BeanDefinition> buildBeanDefinitionList();


    /**
     * 注册对象属性列表
     * @param beanDefinitionList 对象属性列表
     */
    protected void registerBeanDefinitions(final List<? extends BeanDefinition> beanDefinitionList) {
        if(CollectionUtils.isNotEmpty(beanDefinitionList)) {
            for (BeanDefinition beanDefinition : beanDefinitionList) {
                // 填充默认值
                this.fillDefaultValue(beanDefinition);

                super.registerBeanDefinition(beanDefinition.getName(), beanDefinition);
            }
        }
    }

    /**
     * 填充默认值信息
     * @param beanDefinition 对象属性定义
     */
    private void fillDefaultValue(BeanDefinition beanDefinition) {
        String scope = beanDefinition.getScope();
        if(StringUtils.isEmpty(scope)) {
            beanDefinition.setScope(ScopeEnum.SINGLETON.getCode());
        }
    }

    /**
     * 注册关闭钩子函数
     * @since 0.0.4
     */
    protected void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(AbstractApplicationContext.this::destroy));
    }


}
