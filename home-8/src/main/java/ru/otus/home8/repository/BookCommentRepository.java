package ru.otus.home8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.home8.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends MongoRepository<BookComment, String> {
    List<BookComment> findByBookId(String bookId);
}
