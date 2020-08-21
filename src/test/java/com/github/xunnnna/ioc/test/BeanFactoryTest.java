package com.github.xunnnna.ioc.test;

import com.github.xunnnna.ioc.context.ClassPathJsonApplicationContext;
import com.github.xunnnna.ioc.core.BeanFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhutingxuan on 2020/8/21.
 */
public class BeanFactoryTest {

    private final BeanFactory beanFactory = new ClassPathJsonApplicationContext("apple.json");

    @Test
    public void getBean() {
        Apple apple = (Apple) beanFactory.getBean("apple");
        apple.eat();
    }

    @Test
    public void getBeanByNameType() {
        Apple apple = beanFactory.getBean("apple", Apple.class);
        apple.eat();
    }

    @Test
    public void containsBean() {
        Assert.assertTrue(beanFactory.containsBean("apple"));
        Assert.assertFalse(beanFactory.containsBean("orange"));
    }

    @Test
    public void isTypeMatch() {
        Assert.assertTrue(beanFactory.isTypeMatch("apple", Apple.class));
        Assert.assertTrue(beanFactory.isTypeMatch("apple", BeanFactory.class));
    }

    @Test
    public void getType() {
        Assert.assertEquals(beanFactory.getType("apple"), Apple.class);
    }

    /**
     * 单例测试
     */
    @Test
    public void singletonTest() {
        BeanFactory singleton = new ClassPathJsonApplicationContext("singleton/apple-singleton.json");
        Apple singletonOne = singleton.getBean("apple", Apple.class);
        Apple singletonTwo = singleton.getBean("apple", Apple.class);
        Assert.assertSame(singletonOne, singletonTwo);
    }

    /**
     * 多例测试
     */
    @Test
    public void prototypeTest() {
        BeanFactory singleton = new ClassPathJsonApplicationContext("singleton/apple-prototype.json");
        Apple singletonOne = singleton.getBean("apple", Apple.class);
        Apple singletonTwo = singleton.getBean("apple", Apple.class);
        Assert.assertNotSame(singletonOne, singletonTwo);
    }


}