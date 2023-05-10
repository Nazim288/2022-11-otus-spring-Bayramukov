package ru.otus.home8.service.impl;

import com.mongodb.MongoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home8.domain.Author;
import ru.otus.home8.domain.Book;
import ru.otus.home8.domain.BookComment;
import ru.otus.home8.domain.Genre;
import ru.otus.home8.dto.BookDto;
import ru.otus.home8.repository.AuthorRepository;
import ru.otus.home8.repository.BookCommentRepository;
import ru.otus.home8.repository.BookRepository;
import ru.otus.home8.repository.GenreRepository;
import ru.otus.home8.service.BookService;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private BookCommentRepository bookCommentRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void createBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());

        if (nonNull(bookDto.getAuthorId())) {
            Author author = authorRepository.findById(bookDto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found with id " + bookDto.getAuthorId()));
            book.setAuthor(author);
        }
        if (nonNull(bookDto.getGenreId())) {
            Genre genre = genreRepository.findById(bookDto.getGenreId())
                    .orElseThrow(() -> new RuntimeException("Genre not found with id " + bookDto.getGenreId()));
            book.setGenre(genre);
        }
        bookRepository.save(book);
    }

    public void updateBook(String id, BookDto dto) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setName(dto.getName());
            if(nonNull(dto.getAuthorId())){
                Author author = authorRepository.findById(dto.getAuthorId())
                        .orElseThrow(() -> new RuntimeException("Author not found with id " + dto.getAuthorId()));
                existingBook.setAuthor(author);
            }
            if(nonNull(dto.getGenreId())){
                Genre genre = genreRepository.findById(dto.getAuthorId())
                        .orElseThrow(() -> new RuntimeException("Genre not found with id " + dto.getGenreId()));
                existingBook.setGenre(genre);
            }
            bookRepository.save(existingBook);
        }
    }

    public boolean deleteBook(String id) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            bookRepository.delete(existingBook);
            return true;
        } else {
            return false;
        }
    }

    public void addComment(String bookId, String value) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));
        BookComment comment = new BookComment();
        comment.setValue(value);
        comment.setBookId(book.getId());
        bookCommentRepository.save(comment);
    }

    public void deleteCommentById(String commentId) {
        Optional<BookComment> commentOptional = bookCommentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            bookCommentRepository.deleteById(commentId);
        } else {
            throw new MongoException("Comment not found with id: " + commentId);
        }
    }

    public List<BookComment> getComments(String bookId) {
        return bookCommentRepository.findByBookId(bookId);
    }

}
