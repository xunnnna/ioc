package com.github.xunnnna.ioc.test;

import com.github.xunnnna.ioc.context.ClassPathJsonApplicationContext;
import com.github.xunnnna.ioc.core.ListableBeanFactory;
import org.junit.Test;

import java.util.List;
import java.util.jar.JarOutputStream;

import static org.junit.Assert.*;

/**
 * Created by zhutingxuan on 2020/8/21.
 */
public class ListableBeanFactoryTest {

    private final ListableBeanFactory BEAN_FACTORY = new ClassPathJsonApplicationContext("apples.json");


    @Test
    public void getBeans() {
        List<Apple> apples = BEAN_FACTORY.getBeans(Apple.class);
        apples.get(0).eat();
        apples.get(1).eat();
    }
}