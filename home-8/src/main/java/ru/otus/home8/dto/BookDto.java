package ru.otus.home8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private String authorId;
    private String genreId;


    public BookDto(String name, String authorId, String genreId) {
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
    }
}
