package net.senjay.springtest;

import net.senjay.springtest.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestDemo {
    public static void main(String [] args)
    {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Student senjay = (Student)app.getBean("student");
        System.out.println(senjay);
    }

}
