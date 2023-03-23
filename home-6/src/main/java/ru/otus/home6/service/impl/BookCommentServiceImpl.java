package ru.otus.home6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;
import ru.otus.home6.repositories.BookCommentRepository;
import ru.otus.home6.service.BookCommentService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public List<BookComment> getAllByBookId(Long bookId) {
        return bookCommentRepository.findAllCommentsByBookId(bookId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookCommentRepository.delete(id);
    }

    @Override
    @Transactional
    public BookComment update(BookCommentDto dto) {
        return bookCommentRepository.update(dto);
    }

    @Override
    @Transactional
    public BookComment create(BookCommentDto dto) {
        return bookCommentRepository.save(toEntity(dto));
    }

    private BookComment toEntity(BookCommentDto dto) {
        BookComment comment = new BookComment();

        if (nonNull(dto.getBookId())) {
            Optional<Book> book = Optional.ofNullable(em.find(Book.class, dto.getBookId()));
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
