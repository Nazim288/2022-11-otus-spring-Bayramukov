package ru.otus.home8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.home8.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
