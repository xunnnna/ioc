package com.github.xunnnna.ioc.test;

import com.github.xunnnna.ioc.context.ClassPathJsonApplicationContext;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.test.service.Apple;
import org.junit.Test;


/**
 * Created by zhutingxuan on 2020/8/20.
 */
public class ClassPathJsonApplicationContextTest {

    @Test
    public void test() {
        BeanFactory beanFactory = new ClassPathJsonApplicationContext("apple.json");
        Apple apple = beanFactory.getBean("apple", Apple.class);
        apple.eat();
    }
}