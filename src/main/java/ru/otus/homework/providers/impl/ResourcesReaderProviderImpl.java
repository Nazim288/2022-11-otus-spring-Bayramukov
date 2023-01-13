package ru.otus.homework.providers.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ru.otus.homework.enums.ResourceType;
import ru.otus.homework.providers.ResourcesReaderProvider;

import java.io.*;

public class ResourcesReaderProviderImpl implements ResourcesReaderProvider {
    final private ResourceFileNameProvider fileNameProvider;

    public ResourcesReaderProviderImpl(ResourceFileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    @Override
    public CSVReader getCsvReader(ResourceType type) {
        final String fileName = getFileName(type);
        final CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        final ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream != null) {
            InputStreamReader reader = new InputStreamReader(inputStream);

            return new CSVReaderBuilder(new BufferedReader(reader))
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
        } else {
            throw new RuntimeException("Файл resources/" + fileName + " не найден");
        }


    }

    private String getFileName(ResourceType type) {
        if (ResourceType.QUESTION == type) {
            return fileNameProvider.getQuestionsResource();
        } else if (ResourceType.ANSWER == type) {
            return fileNameProvider.getAnswersResource();
        } else if (ResourceType.TASK == type) {
            return fileNameProvider.getTasksResource();
        } else{
            throw new RuntimeException("Некорректно указан " + ResourceType.class.getName());
        }
    }
}
