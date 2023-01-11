package ru.otus.homework.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.otus.homework.dao.QuestionDao;
import ru.otus.homework.domain.Question;
import ru.otus.homework.enums.ResourceType;
import ru.otus.homework.util.ObjectParser;
import ru.otus.homework.providers.ResourcesReaderProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionDaoImpl implements QuestionDao {
    final private ResourcesReaderProvider provider;

    public QuestionDaoImpl(ResourcesReaderProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();

        try (CSVReader csvReader = provider.getCsvReader(ResourceType.QUESTION)) {
            int currentLine = 1;
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                questions.add(convertToQuestion(values, currentLine));
                currentLine++;
            }
        } catch (CsvValidationException | IOException  e) {
            throw new RuntimeException(e + " ошибка при чтении файла с вопросами");
        }

        return questions;
    }

    @Override
    public Question findById(Long id) {
        Question question = new Question();
        int currentLine = 1;

        try (CSVReader csvReader = provider.getCsvReader(ResourceType.QUESTION)) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if(Objects.equals(ObjectParser.toLong(values[0],currentLine, ResourceType.QUESTION), id)){
                    question = convertToQuestion(values, currentLine);
                    continue;
                }
                currentLine++;
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e + " ошибка при чтении файла с ответами , линия " + currentLine );
        }

        return question;
    }

    private Question convertToQuestion(String[] values, int currentLine) {
        if (Question.class.getDeclaredFields().length == values.length) {
            Question question = new Question();
            question.setId(ObjectParser.toLong(values[0],currentLine, ResourceType.QUESTION));
            question.setContent(values[1]);
            return question;
        } else {
            throw new RuntimeException("Имеются лишние или не заполненые поля в файле c вопросами , линия " + currentLine);
        }
    }
}
