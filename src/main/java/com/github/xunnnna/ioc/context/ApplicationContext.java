package com.github.xunnnna.ioc.context;

import com.github.xunnnna.ioc.core.ListableBeanFactory;

/**
 * 应用上下文接口
 * Created by zhutingxuan on 2020/8/21.
 */
public interface ApplicationContext extends ListableBeanFactory {

    /**
     * 获取应用名称
     * @return
     */
    String getApplicationName();
}
