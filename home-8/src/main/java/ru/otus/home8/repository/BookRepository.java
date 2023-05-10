package ru.otus.home8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.home8.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
