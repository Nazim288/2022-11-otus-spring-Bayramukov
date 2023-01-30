package ru.otus.home4.dao.impl;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home4.dao.StudentDao;
import ru.otus.home4.domains.Student;
import ru.otus.home4.enums.ResourceType;
import ru.otus.home4.providers.ResourcesWriterProvider;

import java.io.IOException;

@Component
@AllArgsConstructor
public class StudentDaoImpl implements StudentDao {
    final private ResourcesWriterProvider provider;

    @Override
    public void save(Student student) {
        try (CSVWriter writer = provider.getCsvWriter(ResourceType.STUDENT)) {
            writer.writeNext(studentDataConverter(student));
        } catch (IOException e) {
            throw new RuntimeException("ошибка при сохранении студента", e.fillInStackTrace());
        }

    }

    private String[] studentDataConverter(Student student) {

        return new String[]{student.getName(), student.getLastName(), String.valueOf(student.getPoints())};

    }
}
