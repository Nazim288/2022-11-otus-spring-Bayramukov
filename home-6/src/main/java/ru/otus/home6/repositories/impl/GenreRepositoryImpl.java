package ru.otus.home6.repositories.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.home6.domains.Genre;
import ru.otus.home6.repositories.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@AllArgsConstructor
@Component
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }
}
