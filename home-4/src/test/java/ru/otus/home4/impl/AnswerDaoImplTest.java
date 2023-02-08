package ru.otus.home4.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import ru.otus.home4.dao.impl.AnswerDaoImpl;
import ru.otus.home4.domains.Answer;
import ru.otus.home4.enums.ResourceType;
import ru.otus.home4.providers.impl.ResourcesReaderProviderImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@DisplayName("Должен ")
@ExtendWith(MockitoExtension.class)
class AnswerDaoImplTest {
    @Mock
    private ResourcesReaderProviderImpl provider;

    @InjectMocks
    private AnswerDaoImpl answerDao;

    private ResourceType resourceType;
    private Long answerId;
    private Long existingAnswerId;
    private String answerValue;

    @BeforeEach
    void setUp() {
        existingAnswerId = 0L;
        answerValue = "240";
        answerId = 1L;
        String resource = "data/ru/answers_test.csv";
        resourceType = ResourceType.ANSWER;
        final ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resource);
        final CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        assert inputStream != null;
        InputStreamReader reader = new InputStreamReader(inputStream);
        CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(reader))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();
        given(provider.getCsvReader(any()))
                .willReturn(csvReader);

    }
    @DisplayName("получить Answer по id")
    @Test
    void shouldFindAnswerById() {
        Answer answer = answerDao.findById(answerId);
        verify(provider, times(1)).getCsvReader(resourceType);
        Assert.notNull(answer, "обьект не найден");
        Assertions.assertEquals(answer.getId(), answerId);
        Assertions.assertEquals(answer.getValue(), answerValue);
    }
    @DisplayName("выбросить исключение если Answer по id не найден")
    @Test
    void shouldTrowExceptionIfNotFound() {
        Answer answer = answerDao.findById(existingAnswerId);
        verify(provider, times(1)).getCsvReader(resourceType);
        Assert.isNull(answer, "должен вернуть null если обьекта с таким id не сушествует");

    }
}