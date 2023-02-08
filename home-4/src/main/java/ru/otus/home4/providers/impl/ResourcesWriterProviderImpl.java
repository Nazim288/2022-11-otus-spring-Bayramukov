package ru.otus.home4.providers.impl;

import com.opencsv.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home4.enums.ResourceType;
import ru.otus.home4.providers.ResourcesWriterProvider;

import java.io.*;

@Component
@AllArgsConstructor
public class ResourcesWriterProviderImpl implements ResourcesWriterProvider {
    final private ResourceFileProvider fileProvider;

    @Override
    public CSVWriter getCsvWriter(ResourceType type) throws IOException {
        final String CSV_FILE = getFilePath(type);
        File root = new File(fileProvider.getResourcesPath());
        FileWriter fileWriter = new FileWriter(getAbsolutePath(root.getAbsolutePath(), CSV_FILE), true);
        return new CSVWriter(fileWriter);

    }

    private String getAbsolutePath(String root, String fileName) {
        return root.concat("/").concat(fileName);
    }


    public String getFilePath(ResourceType type) {
        if (ResourceType.EXAM == type) {
            return fileProvider.getExamResource();
        } else if (ResourceType.STUDENT == type) {
            return fileProvider.getStudentResource();
        } else {
            throw new RuntimeException("Некорректно указан тип" + ResourceType.class.getName());
        }
    }
}


