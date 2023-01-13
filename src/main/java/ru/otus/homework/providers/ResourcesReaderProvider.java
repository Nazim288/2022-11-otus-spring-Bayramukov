package ru.otus.homework.providers;

import com.opencsv.CSVReader;
import ru.otus.homework.enums.ResourceType;

public interface ResourcesReaderProvider {
    CSVReader getCsvReader(ResourceType type);

}
