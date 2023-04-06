package ru.otus.home6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.home6.domains.Author;
import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.domains.Genre;
import ru.otus.home6.dto.BookDto;
import ru.otus.home6.repositories.AuthorRepository;
import ru.otus.home6.repositories.BookRepository;
import ru.otus.home6.repositories.GenreRepository;
import ru.otus.home6.service.BookService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

    @Override
    @Transactional
    public Book update(BookDto dto) {
        Optional<Book> optionalBook = bookRepository.findById(dto.getId());

        if (optionalBook.isPresent()) {
            Book updatedBook = optionalBook.get();
            if (nonNull(dto.getName())) {
                updatedBook.setName(dto.getName());
            }
            if (nonNull(dto.getGenreId())) {
                Optional<Genre> genre = genreRepository.findById(dto.getGenreId());
                if (genre.isPresent()) {
                    updatedBook.setGenre(genre.get());
                } else {
                    throw new EntityNotFoundException(Genre.class.getName());
                }
            }

            if (nonNull(dto.getAuthorId())) {
                Optional<Author> author = authorRepository.findById(dto.getAuthorId());
                if (author.isPresent()) {
                    updatedBook.setAuthor(author.get());
                } else {
                    throw new EntityNotFoundException(Author.class.getName());
                }
            }
            bookRepository.save(updatedBook);
            return optionalBook.get();

        } else {
            throw new EntityNotFoundException(Book.class.getName());
        }


    }

    @Override
    @Transactional
    public List<String> getAllComments(Long id) {
        return bookRepository.findAllComments(id).stream().map(BookComment::getValue).collect(Collectors.toList());
    }

    @Override
    public Book getById(Long id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Override
    @Transactional
    public Book create(BookDto dto) {
        return bookRepository.save(toEntity(dto));
    }

    private Book toEntity(BookDto dto) {
        Book book = new Book();

        if (nonNull(dto.getName())) {
            book.setName(dto.getName());
        }
        if (nonNull(dto.getGenreId())) {
            Optional<Genre> genre = genreRepository.findById(dto.getGenreId());
            if (genre.isPresent()) {
                book.setGenre(genre.get());
            } else {
                throw new EntityNotFoundException(Genre.class.getName());
            }
        }

        if (nonNull(dto.getAuthorId())) {
            Optional<Author> author = authorRepository.findById(dto.getAuthorId());
            if (author.isPresent()) {
                book.setAuthor(author.get());
            } else {
                throw new EntityNotFoundException(Author.class.getName());
            }
        }

        return book;
    }
}
