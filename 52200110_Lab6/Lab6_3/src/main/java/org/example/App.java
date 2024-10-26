package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.input("Sping is comming!");
        try {
            textEditor.save("spring.txt");
        }catch (IOException e){
            System.err.println(e.getStackTrace());
        }


    }
}
