package ru.otus.home2.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home2.dao.StudentDao;
import ru.otus.home2.domains.Student;
@Component
@AllArgsConstructor
public class StudentDaoImpl implements StudentDao {
    @Override
    public void save(Student student) {

    }
}
