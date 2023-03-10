package ru.otus.home5.domains;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Genre {
    private long id;
    private final String name;

}
