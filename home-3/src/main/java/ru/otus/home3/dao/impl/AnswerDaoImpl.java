package ru.otus.home3.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home3.dao.AnswerDao;
import ru.otus.home3.domains.Answer;
import ru.otus.home3.enums.ResourceType;
import ru.otus.home3.providers.ResourcesReaderProvider;
import ru.otus.home3.util.ObjectParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Component
@AllArgsConstructor
public class AnswerDaoImpl implements AnswerDao {

    final private ResourcesReaderProvider provider;
    @Override
    public List<Answer> findAll() {
        List<Answer> answers = new ArrayList<>();
        int currentLine = 1;

        try (CSVReader csvReader = provider.getCsvReader(ResourceType.ANSWER)) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                answers.add(convertToAnswer(values, currentLine));
                currentLine++;
            }
        } catch (CsvValidationException | IOException  e) {
            throw new RuntimeException(e + " ошибка при чтении файла с ответами, на строке:" + currentLine);
        }

        return answers;
    }

    @Override
    public Answer findById(Long id) {
        Answer answer = null;
        try (CSVReader csvReader = provider.getCsvReader(ResourceType.ANSWER)) {
            int currentLine = 1;
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (Objects.equals(ObjectParser.toLong(values[0], currentLine, ResourceType.ANSWER ), id)) {
                    answer = convertToAnswer(values, currentLine);
                    continue;
                }
                currentLine++;

            }
        } catch (CsvValidationException | IOException  e) {

            throw new RuntimeException(e + " ошибка при чтении файла с ответами");
        }

        return answer;
    }


    private Answer convertToAnswer(String[] values, int currentLine) {
        if (Answer.class.getDeclaredFields().length == values.length) {
            Answer answer = new Answer();
            answer.setId(ObjectParser.toLong(values[0],currentLine, ResourceType.ANSWER));
            answer.setValue(values[1]);
            return answer;
        } else {
            throw new RuntimeException("Имеются лишние или не заполненые поля в файле c ответами , линия " + currentLine);
        }
    }

}
