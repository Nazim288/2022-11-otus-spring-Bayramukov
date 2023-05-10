package ru.otus.home8.service;

import ru.otus.home8.domain.Book;
import ru.otus.home8.domain.BookComment;
import ru.otus.home8.dto.BookDto;

import java.util.List;

public interface BookService {
     List<Book> getAllBooks();
    Book getBookById(String id);
    void createBook(BookDto bookDto);
    void updateBook(String id, BookDto dto);
    boolean deleteBook(String id);
    void addComment(String bookId, String value);
    void deleteCommentById(String commentId);
    List<BookComment> getComments(String bookId);

}
