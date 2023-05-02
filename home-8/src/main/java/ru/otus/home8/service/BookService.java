package ru.otus.home8.service;

import com.mongodb.MongoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.home8.domain.Book;
import ru.otus.home8.domain.BookComment;
import ru.otus.home8.dto.BookDto;
import ru.otus.home8.mapper.BookMapper;
import ru.otus.home8.repository.BookCommentRepository;
import ru.otus.home8.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private BookCommentRepository bookCommentRepository;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    public BookDto getBookById(String id) {
        return bookRepository.findById(id).map(bookMapper::toDto).orElse(null);
    }

    public void createBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());

        bookRepository.save(book);
    }

    public void updateBook(String id, BookDto dto) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setName(dto.getName());
            existingBook.setAuthor(dto.getAuthor());
            existingBook.setGenre(dto.getGenre());
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
        comment.setBookId(bookId);
        bookCommentRepository.save(comment);
        book.getComments().add(comment);
        bookRepository.save(book);
    }

    public void deleteCommentById(String commentId) {
        Optional<BookComment> commentOptional = bookCommentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            BookComment comment = commentOptional.get();
            String bookId = comment.getBookId();
            bookRepository.findById(bookId).ifPresent(book -> {
                book.getComments().remove(comment);
                bookRepository.save(book);
            });
            bookCommentRepository.deleteById(commentId);
        } else {
            throw new MongoException("Comment not found with id: " + commentId);
        }
    }

    public List<BookComment> getComments(String bookId) {
        return bookCommentRepository.findByBookId(bookId);
    }

}
