package ru.otus.home4.providers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ru.otus.home4.enums.ResourceType;

import java.io.IOException;

public interface ResourcesWriterProvider {
    CSVWriter getCsvWriter(ResourceType type) throws IOException;
}
