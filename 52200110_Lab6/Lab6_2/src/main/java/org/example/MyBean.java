package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyBean {
    @Bean
    @Scope("prototype")
    public Product beanPrototypeObj() {
        return new Product(Long.parseLong("1"),"Iphone",100.0,"beautiful");
    }
    @Bean
    @Scope("prototype")
    public Product beanPrototypeConstructor() {
        return new Product(beanPrototypeObj());
    }

    @Bean
    @Scope("singleton")
    public Product beanSingletonObj() {
        return new Product(Long.parseLong("3"),"Samsung",200.0,"beautiful");
    }
}