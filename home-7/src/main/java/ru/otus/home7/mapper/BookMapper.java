package ru.otus.home7.mapper;

import ru.otus.home7.domain.Book;
import ru.otus.home7.dto.BookCreateDto;
import ru.otus.home7.dto.BookDto;
import ru.otus.home7.dto.BookUpdateDto;

public interface BookMapper {
    BookDto toDto(Book book);
    Book toNewEntity(BookCreateDto dto);
    Book toExistEntity(BookUpdateDto dto);
}
