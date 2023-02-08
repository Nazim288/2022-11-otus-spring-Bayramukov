package ru.otus.home4.providers.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home4.config.LocalConfig;
import ru.otus.home4.enums.ApplicationLanguage;
import ru.otus.home4.enums.ResourceType;
import ru.otus.home4.providers.ResourcesReaderProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

@Component
@AllArgsConstructor
public class ResourcesReaderProviderImpl implements ResourcesReaderProvider {
    final private ResourceFileProvider fileNameProvider;
    final private LocalConfig locale;

    @Override
    public CSVReader getCsvReader(ResourceType type) {
        final String filePath = getFilePath(type);
        final CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        final ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream != null) {
            InputStreamReader reader = new InputStreamReader(inputStream);

            return new CSVReaderBuilder(new BufferedReader(reader))
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
        } else {
            throw new RuntimeException("Файл resources/" + filePath + " не найден");
        }


    }


    public String getFilePath(ResourceType type) {
        final String root = getRoot();
        if (ResourceType.QUESTION == type) {
            return root + fileNameProvider.getQuestionsResource();
        } else if (ResourceType.ANSWER == type) {
            return root + fileNameProvider.getAnswersResource();
        } else if (ResourceType.TASK == type) {
            return root + fileNameProvider.getTasksResource();
        } else {
            throw new RuntimeException("Некорректно указан тип" + ResourceType.class.getName());
        }
    }
    private String getRoot() {
        if (locale.getLocale().getLanguage().equalsIgnoreCase(ApplicationLanguage.RU.getValue())) {
            return ApplicationLanguage.RU.getRoot();
        } else if (locale.getLocale().getLanguage().equalsIgnoreCase(ApplicationLanguage.EN.getValue())) {
            return ApplicationLanguage.EN.getRoot();
        } else {
            throw new RuntimeException("Некорректно указан параметр в классе " + Locale.class.getName());
        }
    }



}
