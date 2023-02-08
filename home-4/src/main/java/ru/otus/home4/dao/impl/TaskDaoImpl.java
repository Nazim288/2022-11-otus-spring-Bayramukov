package ru.otus.home4.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home4.dao.AnswerDao;
import ru.otus.home4.dao.QuestionDao;
import ru.otus.home4.dao.TaskDao;
import ru.otus.home4.domains.Task;
import ru.otus.home4.enums.ResourceType;
import ru.otus.home4.providers.ResourcesReaderProvider;
import ru.otus.home4.util.ObjectParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class TaskDaoImpl implements TaskDao {
    final private ResourcesReaderProvider provider;
    final private AnswerDao answerDao;
    final private QuestionDao questionDao;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        int currentLine = 1;

        try (CSVReader csvReader = provider.getCsvReader(ResourceType.TASK)) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                tasks.add(convertToTask(values, currentLine));
                currentLine++;
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e + " ошибка при чтении файла с тестовыми задачами " + currentLine);
        }

        return tasks;
    }

    @Override
    public Task findById(Long id) {
        Task task = null;
        try (CSVReader csvReader = provider.getCsvReader(ResourceType.TASK)) {
            int currentLine = 1;
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (Objects.equals(ObjectParser.toLong(values[0], currentLine, ResourceType.TASK ), id)) {
                    task = convertToTask(values, currentLine);
                    continue;
                }
                currentLine++;

            }
        } catch (CsvValidationException | IOException  e) {

            throw new RuntimeException(e + " ошибка при чтении файла с ответами");
        }

        return task;
    }



    private Task convertToTask(String[] values, int currentLine) {
        if (Task.class.getDeclaredFields().length == values.length) {
            Task task = new Task();
            task.setTaskNumber(values[1]);
            task.setQuestion(questionDao.findById(ObjectParser.toLong(values[2],currentLine, ResourceType.TASK)));
            task.setAnswer(answerDao.findById(ObjectParser.toLong(values[3],currentLine, ResourceType.TASK)));
            task.setNumberOfPoints(ObjectParser.toLong(values[4], currentLine, ResourceType.TASK));
            return task;
        } else {
            throw new RuntimeException("Имеются лишние или не заполненые поля в файле c ответами , линия " + currentLine);
        }

    }
}
