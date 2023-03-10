package ru.otus.home5.domains;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Book {
    private long id;
    private final String name;
    private final Long authorId;
    private final Long genreId;

}
