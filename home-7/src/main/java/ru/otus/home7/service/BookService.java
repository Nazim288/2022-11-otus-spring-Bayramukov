package ru.otus.home7.service;


import ru.otus.home7.domain.Book;
import ru.otus.home7.dto.BookCreateDto;
import ru.otus.home7.dto.BookDto;
import ru.otus.home7.dto.BookUpdateDto;

import java.util.List;


public interface BookService {
    List<BookDto> getAll();

    BookDto getById(Long id);

    Book create(BookCreateDto dto);

    Book update(BookUpdateDto dto);

    void delete(Long id);
}
