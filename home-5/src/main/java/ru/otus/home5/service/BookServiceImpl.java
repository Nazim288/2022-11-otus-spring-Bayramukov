package ru.otus.home5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home5.dao.BookDao;
import ru.otus.home5.domains.Book;
import ru.otus.home5.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        return toDto(bookDao.getById(id));
    }

    private BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        dto.setGenre(book.getGenre());
        dto.setAuthor(book.getAuthor());
        return dto;
    }
}
