package ru.otus.home6.repositories;

import ru.otus.home6.domains.Author;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(Long id);

}
