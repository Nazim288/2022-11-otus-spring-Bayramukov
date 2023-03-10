package ru.otus.home5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.home5.domains.Author;
import ru.otus.home5.domains.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String name;
    private Author author;
    private Genre genre;
}
