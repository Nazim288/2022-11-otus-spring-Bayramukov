package ru.otus.homework.util;

import ru.otus.homework.enums.ResourceType;

public class ObjectParser {
    public static Long toLong(String string, int currentLine, ResourceType type) {

        Long result = null;
        try {
            result = Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e + "не коректно заполнено значение в файле с типами "
                    + type.name()
                    + " на линии"
            + currentLine);
        }

        return result;
    }

}
