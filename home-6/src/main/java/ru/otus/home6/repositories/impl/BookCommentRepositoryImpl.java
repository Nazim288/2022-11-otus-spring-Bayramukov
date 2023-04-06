package ru.otus.home6.repositories.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home6.domains.BookComment;
import ru.otus.home6.repositories.BookCommentRepository;

import javax.persistence.*;
import java.util.Optional;


@Component
@AllArgsConstructor
public class BookCommentRepositoryImpl implements BookCommentRepository {
    private final EntityManager em;

    @Override
    public void delete(BookComment comment) {
        em.remove(comment);

    }

    @Override
    public Optional<BookComment> findById(Long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));

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
