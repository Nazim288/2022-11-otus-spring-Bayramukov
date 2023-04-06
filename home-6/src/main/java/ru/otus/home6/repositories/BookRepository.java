package ru.otus.home6.repositories;

import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    List<BookComment> findAllComments(Long bookId);

    Optional<Book> findById(Long id);

    void delete(Book book);

    Book save(Book book);
}
