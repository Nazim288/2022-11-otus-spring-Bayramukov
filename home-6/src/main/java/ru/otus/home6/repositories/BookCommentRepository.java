package ru.otus.home6.repositories;

import ru.otus.home6.domains.BookComment;

import java.util.Optional;

public interface BookCommentRepository {

    void delete(BookComment comment);
    Optional<BookComment> findById(Long id);

    BookComment save(BookComment comment);

}
