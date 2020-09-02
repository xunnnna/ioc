package com.github.xunnnna.ioc.test;

import com.github.xunnnna.ioc.context.ClassPathJsonApplicationContext;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.test.service.Apple;
import com.github.xunnnna.ioc.test.service.Car;
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

    @Test
    public void valuePropertyInjectTest() {
        BeanFactory beanFactory = new ClassPathJsonApplicationContext("create/valueBean.json");
        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car);
    }

    @Test
    public void refPropertyInjectTest() {
        BeanFactory beanFactory = new ClassPathJsonApplicationContext("create/valueBean.json");
        Car car = beanFactory.getBean("carWithWheel", Car.class);
        System.out.println(car);
    }
}