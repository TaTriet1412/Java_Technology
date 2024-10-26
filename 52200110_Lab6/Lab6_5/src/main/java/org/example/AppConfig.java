package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("123")
    private String id;

    @Value("Samsung")
    private String name;

    @Value("2000")
    private String price;

    @Value("Ultraphone")
    private String description;

    @Bean
    @Scope("prototype")
    public Product Product1(){
        return new Product(
                Long.valueOf(this.id),
                this.name,
                Double.valueOf(this.price),
                this.description
        );
    }

    @Bean
    @Scope("prototype")
    public Product Product2(){
        Product p = new Product(Long.valueOf(this.id),
                this.name,
                Double.valueOf(this.price),
                this.description);
        return new Product(p);
    }

    @Bean
    @Scope("singleton")
    public Product Product3(){
        return new Product(
                Long.valueOf(this.id),
                "Xbox",
                Double.valueOf(2000),
                "new product 10/2024"
        );
    }
}
