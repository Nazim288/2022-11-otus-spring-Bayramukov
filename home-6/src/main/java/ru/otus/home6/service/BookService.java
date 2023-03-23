package ru.otus.home6.service;

import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    void delete(Long id);

    Book update(BookDto dto);

    List<BookComment> getAllComments(Long id);

    Book getById(Long id);

    Book create(BookDto dto);

}
