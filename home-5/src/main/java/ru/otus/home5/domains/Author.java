package ru.otus.home5.domains;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {
    private long id;
    private final String name;
    private final String lastName;

}
