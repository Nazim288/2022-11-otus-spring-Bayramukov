package ru.otus.home8.mapper;

import ru.otus.home8.domain.Book;
import ru.otus.home8.dto.BookDto;

public interface BookMapper {
    BookDto toDto(Book book);

}
