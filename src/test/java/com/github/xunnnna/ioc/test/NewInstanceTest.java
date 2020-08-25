package com.github.xunnnna.ioc.test;

import com.github.xunnnna.ioc.context.ClassPathJsonApplicationContext;
import com.github.xunnnna.ioc.core.BeanFactory;
import com.github.xunnnna.ioc.test.service.ColorApple;
import com.github.xunnnna.ioc.test.service.ColorWeightApple;
import org.junit.Test;

/**
 * Created by zhutingxuan on 2020/8/25.
 */
public class NewInstanceTest {

    @Test
    public void factoryMethodAnnotationTest() {
        BeanFactory beanFactory = new ClassPathJsonApplicationContext("create/colorApple-factory.json");
        ColorApple apple = beanFactory.getBean("apple", ColorApple.class);
        System.out.println(apple);
    }

    @Test
    public void constructorTest() {
        BeanFactory beanFactory = new ClassPathJsonApplicationContext("create/colorApple.json");
        ColorWeightApple weightApple = beanFactory.getBean("weightApple", ColorWeightApple.class);
        System.out.println(weightApple);
    }


}
