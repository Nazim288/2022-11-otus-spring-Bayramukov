package ru.otus.home6.repositories.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.home6.domains.Genre;
import ru.otus.home6.repositories.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }
}
