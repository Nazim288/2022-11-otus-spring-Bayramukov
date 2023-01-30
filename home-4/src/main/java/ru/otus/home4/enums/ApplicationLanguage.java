package ru.otus.home4.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationLanguage {
    RU("ru", "data/ru/"),
    EN("en", "data/en/");
    private final String value;
    private final String root;

}
