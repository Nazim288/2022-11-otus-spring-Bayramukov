package ru.otus.home3.providers;

import com.opencsv.CSVReader;
import ru.otus.home3.enums.ResourceType;

public interface ResourcesReaderProvider {
    CSVReader getCsvReader(ResourceType type);

}
