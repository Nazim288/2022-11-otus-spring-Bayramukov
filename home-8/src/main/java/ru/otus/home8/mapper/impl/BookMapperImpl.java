package ru.otus.home8.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home8.domain.Book;
import ru.otus.home8.dto.BookDto;
import ru.otus.home8.mapper.BookMapper;

@Component
@AllArgsConstructor
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        return dto;
    }

}
