package ru.otus.home5.service;

import ru.otus.home5.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAll();

    BookDto getById(Long id);
}
