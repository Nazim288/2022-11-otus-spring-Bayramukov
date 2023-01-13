package ru.otus.homework.dao.impl;

import com.opencsv.exceptions.CsvValidationException;
import ru.otus.homework.dao.AnswerDao;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.domain.Task;
import ru.otus.homework.enums.ResourceType;
import ru.otus.homework.util.ObjectParser;
import ru.otus.homework.providers.ResourcesReaderProvider;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {
    final private ResourcesReaderProvider provider;
    final private AnswerDao answerDao;
    final private QuestionDao questionDao;


    public TaskDaoImpl(ResourcesReaderProvider provider, AnswerDao answerDao, QuestionDao questionDao) {
        this.provider = provider;
        this.answerDao = answerDao;
        this.questionDao = questionDao;
    }

    @Override
    public List<Task> getAll() {
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
