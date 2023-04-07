package ru.otus.home7.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home7.domain.Author;
import ru.otus.home7.domain.Book;
import ru.otus.home7.domain.Genre;
import ru.otus.home7.dto.BookCreateDto;
import ru.otus.home7.dto.BookDto;
import ru.otus.home7.dto.BookUpdateDto;
import ru.otus.home7.mapper.BookMapper;
import ru.otus.home7.repository.AuthorRepository;
import ru.otus.home7.repository.BookRepository;
import ru.otus.home7.repository.GenreRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        if (nonNull(book)) {
            dto.setId(book.getId());
            dto.setName(book.getName());
            if (nonNull(book.getGenre())) {
                dto.setGenre(book.getGenre().getName());
            }
            if (nonNull(book.getAuthor())) {
                dto.setAuthorName(book.getAuthor().getName() + " " + book.getAuthor().getLastName());
            }
        }
        return dto;
    }

    @Override
    public Book toNewEntity(BookCreateDto dto) {
        Book book = new Book();
        if (nonNull(dto)) {
            if (nonNull(dto.getName())) {
                book.setName(dto.getName());
            } else {
                throw new RuntimeException("Нельзя создать книгу без имени");
            }

            if (nonNull(dto.getGenreId())) {
                Optional<Genre> genre = genreRepository.findById(dto.getGenreId());
                genre.ifPresent(book::setGenre);
                genre.orElseThrow(() -> new EntityNotFoundException("Genre not found with id " + dto.getGenreId()));

            }
            if (nonNull(dto.getAuthorId())) {
                Optional<Author> author = authorRepository.findById(dto.getAuthorId());
                author.ifPresent(book::setAuthor);
                author.orElseThrow(() -> new EntityNotFoundException("Author not found with id " + dto.getAuthorId()));

            }
        }

        return book;
    }

    @Override
    public Book toExistEntity(BookUpdateDto dto) {
        if (nonNull(dto)) {
            if (nonNull(dto.getId())) {
                Optional<Book> bookOptional = bookRepository.findById(dto.getId());

                bookOptional.ifPresent(book -> update(book, dto));
            }

        }

        return null;
    }

    private void update(Book book, BookUpdateDto dto) {

        if (nonNull(dto.getName())) {
            book.setName(dto.getName());
        }
        if (nonNull(dto.getGenreId())) {
            Optional<Genre> optionalGenre = genreRepository.findById(dto.genreId);
            optionalGenre.ifPresent(book::setGenre);

            optionalGenre.orElseThrow(() -> new EntityNotFoundException("Genre not found with id " + dto.getGenreId()));
        }
    }
}
