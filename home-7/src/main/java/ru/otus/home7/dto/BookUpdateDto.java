package ru.otus.home7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookUpdateDto {
    public Long id;
    public String name;
    public Long genreId;
}
