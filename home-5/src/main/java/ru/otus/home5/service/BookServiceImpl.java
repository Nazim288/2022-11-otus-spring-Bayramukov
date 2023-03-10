package ru.otus.home5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home5.dao.AuthorDao;
import ru.otus.home5.dao.BookDao;
import ru.otus.home5.dao.GenreDao;
import ru.otus.home5.domains.Book;
import ru.otus.home5.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        dto.setGenre(genreDao.getById(book.getGenreId()));
        dto.setAuthor(authorDao.getById(book.getAuthorId()));
        return dto;
    }
}
