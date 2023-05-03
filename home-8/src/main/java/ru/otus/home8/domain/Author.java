package ru.otus.home8.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Document(collection = "author")

public class Author {
    @Id
    private String id;

    private String name;

    private String lastName;

}