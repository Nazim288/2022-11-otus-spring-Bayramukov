package ru.otus.home7.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.home7.domain.Book;
import ru.otus.home7.domain.BookComment;
import ru.otus.home7.repository.BookCommentRepository;
import ru.otus.home7.repository.BookRepository;
import ru.otus.home7.service.BookCommentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> getAllCommentByBookId(Long id) {
        return bookCommentRepository.findBookCommentByBookId(id);
    }

    @Override
    public void delete(Long id) {
        bookCommentRepository.deleteById(id);
    }

    @Override
    public void addComment(Long bookId, String text) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        optionalBook.ifPresent(comment -> {
            BookComment bookComment = new BookComment();
            bookComment.setBook(optionalBook.get());
            bookComment.setValue(text);
            bookCommentRepository.save(bookComment);
        });
        optionalBook.orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));

    }


}
