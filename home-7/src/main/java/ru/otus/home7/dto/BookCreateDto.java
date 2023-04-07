package ru.otus.home7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCreateDto {
    public String name;
    public Long authorId;
    public Long genreId;
}
