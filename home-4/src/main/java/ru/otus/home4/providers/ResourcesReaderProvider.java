package ru.otus.home4.providers;

import com.opencsv.CSVReader;
import ru.otus.home4.enums.ResourceType;

public interface ResourcesReaderProvider {
    CSVReader getCsvReader(ResourceType type);

}
