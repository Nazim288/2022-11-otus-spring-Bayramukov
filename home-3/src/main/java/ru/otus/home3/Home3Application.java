package ru.otus.home3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.home3.services.impl.ExamServiceImpl;

@SpringBootApplication
public class Home3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Home3Application.class, args);
        run.getBean(ExamServiceImpl.class).runExam();    }

}
