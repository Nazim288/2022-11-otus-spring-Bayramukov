package ru.otus.home2.providers;

import com.opencsv.CSVReader;
import ru.otus.home2.enums.ResourceType;

public interface ResourcesReaderProvider {
    CSVReader getCsvReader(ResourceType type);

}
