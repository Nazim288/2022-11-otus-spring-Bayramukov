package ru.otus.home8.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home8.domain.Book;
import ru.otus.home8.dto.BookDto;
import ru.otus.home8.mapper.BookMapper;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        if (nonNull(book)) {
            dto.setId(book.getId());
            dto.setName(book.getName());
            if (nonNull(book.getGenre())) {
                dto.setGenre(book.getGenre());
            }
            if (nonNull(book.getAuthor())) {
                dto.setAuthor(book.getAuthor());
            }
        }
        return dto;
    }

}
