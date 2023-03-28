package ru.otus.home6.repositories;

import ru.otus.home6.domains.Genre;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(Long id);

}
