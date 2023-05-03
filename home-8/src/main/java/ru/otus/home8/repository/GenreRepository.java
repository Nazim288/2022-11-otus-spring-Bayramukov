package ru.otus.home8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.home8.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
