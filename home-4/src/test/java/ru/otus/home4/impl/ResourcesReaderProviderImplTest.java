package ru.otus.home4.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.home4.config.LocalConfig;
import ru.otus.home4.enums.ApplicationLanguage;
import ru.otus.home4.providers.impl.ResourceFileProvider;

import java.util.Arrays;
import java.util.Locale;

@DisplayName("должен")
@SpringBootTest
class ResourcesReaderProviderImplTest {
    @Autowired
    private LocalConfig localConfig;

    @Autowired
    private ResourceFileProvider resourceFileProvider;


    @Test
    @DisplayName( "проверить что значение локального языка передаются корректно")
    void checkThatLocaleSetting() {
        Locale locale = localConfig.getLocale();
        Assertions.assertNotNull(locale.getLanguage());
        boolean b = Arrays.stream(ApplicationLanguage.values()).anyMatch(value -> value.getValue().equals(locale.getLanguage()));
        Assertions.assertTrue(b);
    }
    @Test
    @DisplayName( "проверить что имена файлов передаются")
    void checkThatFileNamesSetting() {
       String answer = resourceFileProvider.getAnswersResource();
       Assertions.assertNotNull(answer);
       String task = resourceFileProvider.getTasksResource();
       Assertions.assertNotNull(task);
       String question = resourceFileProvider.getQuestionsResource();
       Assertions.assertNotNull(question);
       String exam = resourceFileProvider.getExamResource();
       Assertions.assertNotNull(exam);

   }
}