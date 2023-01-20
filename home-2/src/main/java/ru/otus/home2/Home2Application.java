package ru.otus.home2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.home2.services.impl.ExamServiceImpl;

@SpringBootApplication
public class Home2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Home2Application.class, args);
        run.getBean(ExamServiceImpl.class).runExam();

    }

}
