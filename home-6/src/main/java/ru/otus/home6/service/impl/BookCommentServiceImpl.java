package ru.otus.home6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;
import ru.otus.home6.repositories.BookCommentRepository;
import ru.otus.home6.repositories.BookRepository;
import ru.otus.home6.service.BookCommentService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void delete(BookComment comment) {
        bookCommentRepository.delete(comment);
    }

    @Override
    @Transactional
    public BookComment create(BookCommentDto dto) {
        return bookCommentRepository.save(toEntity(dto));
    }

    @Override
    @Transactional
    public BookComment update(BookCommentDto dto) {
        BookComment updatedBookComment;
        Optional<BookComment> comment = bookCommentRepository.findById(dto.getBookId());
        if (comment.isPresent()) {
            updatedBookComment = comment.get();
            if (nonNull(dto.getCommentValue())) {
                updatedBookComment.setValue(dto.getCommentValue());
            }
        } else {
            throw new EntityNotFoundException(Book.class.getName());
        }
        bookCommentRepository.save(updatedBookComment);
        return updatedBookComment;
    }

    private BookComment toEntity(BookCommentDto dto) {
        BookComment comment = new BookComment();

        if (nonNull(dto.getBookId())) {
            Optional<Book> book = bookRepository.findById(dto.getBookId());
            if (book.isPresent()) {
                comment.setBook(book.get());
            } else {
                throw new EntityNotFoundException(Book.class.getName() + " with id " + dto.getBookId());
            }
        }
        if (nonNull(dto.getCommentValue())) {
            comment.setValue(dto.getCommentValue());
        }

        return comment;
    }

}
