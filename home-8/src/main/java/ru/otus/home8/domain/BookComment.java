package ru.otus.home8.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "book_comment")
public class BookComment {
    @Id
    private long id;

    private String value;

}

