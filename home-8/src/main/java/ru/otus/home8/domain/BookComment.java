package ru.otus.home8.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Setter
@Getter
@Document(collection = "book_comment")
public class BookComment {
    @Id
    private String id;

    private String value;

    private String bookId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookComment)) return false;
        BookComment comment = (BookComment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getValue(), comment.getValue()) &&
                Objects.equals(getBookId(), comment.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getBookId());
    }

}