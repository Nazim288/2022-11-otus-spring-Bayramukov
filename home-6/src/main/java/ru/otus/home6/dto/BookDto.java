package ru.otus.home6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private Long author_id;
    private Long genre_id;

    public BookDto(String name, Long author_id, Long genre_id) {
        this.name = name;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }
}
