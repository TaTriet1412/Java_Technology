package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@ComponentScan
@Configuration
public class App 
{
    private static ApplicationContext applicationContext;
    public static void main( String[] args )
    {
        applicationContext = new AnnotationConfigApplicationContext(App.class);
        TextEditor textEditor = (TextEditor) applicationContext.getBean("textEditor");
        textEditor.input("Sping is comming!");
        try {
            textEditor.save("spring.txt");
        }catch (IOException e){
            System.err.println(e.getStackTrace());
        }
    }
}
