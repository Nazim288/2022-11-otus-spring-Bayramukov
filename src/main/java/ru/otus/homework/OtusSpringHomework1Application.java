package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.services.impl.TaskServiceImpl;


public class OtusSpringHomework1Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        context.getBean(TaskServiceImpl.class).printAll();

    }

}
