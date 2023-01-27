package ru.otus.home3.providers.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.home3.config.LocalConfig;
import ru.otus.home3.enums.ApplicationLanguage;

import java.util.Arrays;
import java.util.Locale;

@DisplayName("должен")
@SpringBootTest
class ResourcesReaderProviderImplTest {
    @Autowired
    private LocalConfig localConfig;

    @Autowired
    private ResourceFileNameProvider resourceFileNameProvider;


    @Test
    @DisplayName( "проверить значение локального языка передаются корректно")
    void checkThatLocaleSetting() {
        Locale locale = localConfig.getLocale();
        Assertions.assertNotNull(locale.getLanguage());
        boolean b = Arrays.stream(ApplicationLanguage.values()).anyMatch(value -> value.getValue().equals(locale.getLanguage()));
        Assertions.assertTrue(b);
    }
    @Test
    @DisplayName( "проверить что именм файлов передаются")
    void checkThatFileNamesSetting() {
       String answer = resourceFileNameProvider.getAnswersResource();
       Assertions.assertNotNull(answer);
       String task = resourceFileNameProvider.getTasksResource();
       Assertions.assertNotNull(task);
       String question = resourceFileNameProvider.getQuestionsResource();
       Assertions.assertNotNull(question);
       String exam = resourceFileNameProvider.getExamResource();
       Assertions.assertNotNull(exam);


   }
}