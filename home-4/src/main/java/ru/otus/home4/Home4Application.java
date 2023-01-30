package ru.otus.home4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.home4.dao.impl.StudentDaoImpl;
import ru.otus.home4.domains.Student;


@SpringBootApplication
public class Home4Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Home4Application.class, args);
        run.getBean(StudentDaoImpl.class).save(new Student("iiii", "bhbhbhb", 9));    }

}






