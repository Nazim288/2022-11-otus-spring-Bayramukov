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
import ru.otus.home6.repositories.BookCommentRepository;
import ru.otus.home6.repositories.BookRepository;
import ru.otus.home6.repositories.GenreRepository;
import ru.otus.home6.service.BookService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCommentRepository bookCommentRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    @Transactional
    public Book update(BookDto dto) {
        return bookRepository.update(dto);
    }

    @Override
    @Transactional
    public List<BookComment> getAllComments(Long id) {
        return bookCommentRepository.findAllCommentsByBookId(id);
    }

    @Override
    @Transactional
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
        if (nonNull(dto.getGenre_id())) {
            Optional<Genre> genre = genreRepository.findById(dto.getGenre_id());
            if (genre.isPresent()) {
                book.setGenre(genre.get());
            } else {
                throw new EntityNotFoundException(Genre.class.getName());
            }
        }

        if (nonNull(dto.getAuthor_id())) {
            Optional<Author> author = authorRepository.findById(dto.getAuthor_id());
            if (author.isPresent()) {
                book.setAuthor(author.get());
            } else {
                throw new EntityNotFoundException(Author.class.getName());
            }
        }

        return book;
    }
}
