package ru.otus.home6.repositories.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.home6.domains.Author;
import ru.otus.home6.repositories.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@AllArgsConstructor
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

}
