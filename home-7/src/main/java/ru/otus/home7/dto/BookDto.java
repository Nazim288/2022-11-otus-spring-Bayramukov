package ru.otus.home7.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String authorName;
    private String genre;
}
