package ru.otus.home6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private Long authorId;
    private Long genreId;

    public BookDto(String name, Long author_id, Long genre_id) {
        this.name = name;
        this.authorId = author_id;
        this.genreId = genre_id;
    }
}
