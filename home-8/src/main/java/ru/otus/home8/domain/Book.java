package ru.otus.home8.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "book")
public class Book {
    @Id
    private String id;

    private String name;

    private String author;

    private String genre;

    private List<BookComment> comments = new ArrayList<>();
}


