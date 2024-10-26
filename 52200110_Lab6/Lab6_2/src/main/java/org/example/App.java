package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyBean myBean = new MyBean();
        Product product1 = myBean.beanPrototypeObj();
        Product product2 = myBean.beanPrototypeConstructor();
        Product product3 = myBean.beanSingletonObj();

        System.out.println("Name of the bean 1: " + product1.getName());
        System.out.println("Name of the bean 2: " + product2.getName());
        System.out.println("Name of the bean 3: " + product3.getName());
    }
}
