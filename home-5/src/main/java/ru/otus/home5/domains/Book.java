package ru.otus.home5.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private long id;
    private String name;
    private Author author;
    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
