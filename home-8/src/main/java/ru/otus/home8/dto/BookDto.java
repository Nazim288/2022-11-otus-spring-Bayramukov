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
    private String author;
    private String genre;


    public BookDto(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
