package ru.otus.home6.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.home6.domains.Book;
import ru.otus.home6.dto.BookDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    void delete(Long id);

    Book update(BookDto dto);

    Book save(Book book);
}
