package ru.otus.home6.repositories.impl;

import org.springframework.stereotype.Repository;
import ru.otus.home6.domains.Book;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.dto.BookCommentDto;
import ru.otus.home6.repositories.BookCommentRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Repository
public class BookCommentRepositoryImpl implements BookCommentRepository {
    @PersistenceContext
    private final EntityManager em;

    public BookCommentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<BookComment> findAllCommentsByBookId(Long bookId) {
        TypedQuery<BookComment> query = em.createQuery("select bc from BookComment bc where bc.book.id = :bookId", BookComment.class);
        query.setParameter("bookId", bookId);

        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete " +
                "from BookComment bc " +
                "where bc.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public BookComment update(BookCommentDto dto) {
        BookComment updatedBookComment;
        Optional<BookComment> comment = Optional.ofNullable(em.find(BookComment.class, dto.getId()));

        if (comment.isPresent()) {
            updatedBookComment = comment.get();
            if (nonNull(dto.getCommentValue())) {
                updatedBookComment.setValue(dto.getCommentValue());
            }
        } else {
            throw new EntityNotFoundException(Book.class.getName());
        }
        em.persist(updatedBookComment);
        return updatedBookComment;
    }

    @Override
    public BookComment save(BookComment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }
}
