package ru.otus.home5.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {
    private long id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
